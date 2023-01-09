package org.jboss.tools.hibernate.runtime.v_6_2.internal;

import org.jboss.tools.hibernate.runtime.common.AbstractFacadeFactory;
import org.jboss.tools.hibernate.runtime.spi.IPersistentClass;
import org.jboss.tools.hibernate.runtime.spi.IProperty;
import org.jboss.tools.hibernate.runtime.spi.IReverseEngineeringStrategy;

public class FacadeFactoryImpl  extends AbstractFacadeFactory {

	@Override
	public ClassLoader getClassLoader() {
		return FacadeFactoryImpl.class.getClassLoader();
	}

	@Override
	public IReverseEngineeringStrategy createReverseEngineeringStrategy(Object target) {
		return new ReverseEngineeringStrategyFacadeImpl(this, target);
	}

	@Override
	public IPersistentClass createSpecialRootClass(IProperty arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}