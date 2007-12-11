/*******************************************************************************
 * Copyright (c) 2007 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributor:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.hibernate.eclipse.console.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.ObjectPluginAction;
import org.hibernate.console.ConsoleConfiguration;
import org.hibernate.eclipse.console.HibernateConsolePlugin;
import org.hibernate.eclipse.console.utils.ProjectUtils;
import org.hibernate.mapping.RootClass;

/**
 * @author Dmitry Geraskov
 * @deprecated - Can't shine selected element in the Editor.
 * 				Use OpenSourceAction instead of this.
 */
public class OpenSourceActionDelegate extends OpenActionDelegate {

	public void run(IAction action) {
    	ObjectPluginAction objectPluginAction = (ObjectPluginAction)action;
    	RootClass rootClass = (RootClass)((TreeSelection)objectPluginAction.getSelection()).getFirstElement();
		ConsoleConfiguration consoleConfiguration = (ConsoleConfiguration)(((TreeSelection)objectPluginAction.getSelection()).getPaths()[0]).getSegment(0);
		IJavaProject proj = ProjectUtils.findJavaProject(consoleConfiguration);

		IResource resource = null;
		String fullyQualifiedName = OpenFileActionUtils.getPersistentClassName(rootClass);
		try {
			IType type = proj.findType(fullyQualifiedName);
			if (type != null) resource = type.getResource();
		} catch (JavaModelException e) {
			HibernateConsolePlugin.getDefault().logErrorMessage("Can't find source file.", e);
		}
		
		if (resource instanceof IFile){
            try {
            	OpenFileActionUtils.openEditor(HibernateConsolePlugin.getDefault().getActiveWorkbenchWindow().getActivePage(), (IFile) resource);
            } catch (PartInitException e) {
    			HibernateConsolePlugin.getDefault().logErrorMessage("Can't open source file.", e);
            }               
        }
		if (resource == null) {
			MessageDialog.openInformation(HibernateConsolePlugin.getDefault().getShell(), "Open Source File", "Source file for class '" + fullyQualifiedName + "' not found.");
		}
	}
}