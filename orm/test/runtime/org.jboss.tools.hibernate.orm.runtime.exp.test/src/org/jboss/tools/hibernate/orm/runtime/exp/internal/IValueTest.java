package org.jboss.tools.hibernate.orm.runtime.exp.internal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.mapping.Collection;
import org.hibernate.mapping.Component;
import org.hibernate.mapping.ManyToOne;
import org.hibernate.mapping.SimpleValue;
import org.hibernate.mapping.Table;
import org.hibernate.mapping.Value;
import org.hibernate.type.ArrayType;
import org.hibernate.type.BagType;
import org.hibernate.type.BasicType;
import org.hibernate.type.ComponentType;
import org.hibernate.type.ListType;
import org.hibernate.type.ManyToOneType;
import org.hibernate.type.MapType;
import org.hibernate.type.OneToOneType;
import org.hibernate.type.SetType;
import org.hibernate.type.Type;
import org.jboss.tools.hibernate.orm.runtime.exp.internal.util.NewFacadeFactory;
import org.jboss.tools.hibernate.runtime.common.IFacade;
import org.jboss.tools.hibernate.runtime.spi.IPersistentClass;
import org.jboss.tools.hibernate.runtime.spi.ITable;
import org.jboss.tools.hibernate.runtime.spi.IType;
import org.jboss.tools.hibernate.runtime.spi.IValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IValueTest {
	
	private IValue arrayValueFacade = null;
	private Value arrayValueTarget = null;
	private IValue bagValueFacade = null;
	private Value bagValueTarget = null;
	private IValue listValueFacade = null;
	private Value listValueTarget = null;
	private IValue manyToOneValueFacade = null;
	private Value manyToOneValueTarget = null;
	private IValue mapValueFacade = null;
	private Value mapValueTarget = null;
	private IValue oneToManyValueFacade = null;
	private Value oneToManyValueTarget = null;
	private IValue oneToOneValueFacade = null;
	private Value oneToOneValueTarget = null;
	private IValue primitiveArrayValueFacade = null;
	private Value primitiveArrayValueTarget = null;
	private IValue setValueFacade = null;
	private Value setValueTarget = null;
	private IValue simpleValueFacade = null;
	private Value simpleValueTarget = null;
	private IValue componentValueFacade = null;
	private Value componentValueTarget = null;
	
	private IPersistentClass persistentClassFacade = null;
	private ITable tableFacade = null;
	private Table tableTarget = null;
	
	@BeforeEach 
	public void beforeEach() {
		persistentClassFacade = NewFacadeFactory.INSTANCE.createRootClass();
		tableFacade = NewFacadeFactory.INSTANCE.createTable("foo");
		tableTarget = (Table)((IFacade)tableFacade).getTarget();
		arrayValueFacade = NewFacadeFactory.INSTANCE.createArray(persistentClassFacade);
		arrayValueTarget = (Value)((IFacade)arrayValueFacade).getTarget();
		bagValueFacade = NewFacadeFactory.INSTANCE.createBag(persistentClassFacade);
		bagValueTarget = (Value)((IFacade)bagValueFacade).getTarget();
		listValueFacade = NewFacadeFactory.INSTANCE.createList(persistentClassFacade);
		listValueTarget = (Value)((IFacade)listValueFacade).getTarget();
		manyToOneValueFacade = NewFacadeFactory.INSTANCE.createManyToOne(tableFacade);
		manyToOneValueTarget = (Value)((IFacade)manyToOneValueFacade).getTarget();
		mapValueFacade = NewFacadeFactory.INSTANCE.createMap(persistentClassFacade);
		mapValueTarget = (Value)((IFacade)mapValueFacade).getTarget();
		oneToManyValueFacade = NewFacadeFactory.INSTANCE.createOneToMany(persistentClassFacade);
		oneToManyValueTarget = (Value)((IFacade)oneToManyValueFacade).getTarget();
		oneToOneValueFacade = NewFacadeFactory.INSTANCE.createOneToOne(persistentClassFacade);
		oneToOneValueTarget = (Value)((IFacade)oneToOneValueFacade).getTarget();
		primitiveArrayValueFacade = NewFacadeFactory.INSTANCE.createPrimitiveArray(persistentClassFacade);
		primitiveArrayValueTarget = (Value)((IFacade)primitiveArrayValueFacade).getTarget();
		setValueFacade = NewFacadeFactory.INSTANCE.createSet(persistentClassFacade);
		setValueTarget = (Value)((IFacade)setValueFacade).getTarget();
		simpleValueFacade = NewFacadeFactory.INSTANCE.createSimpleValue();
		simpleValueTarget = (Value)((IFacade)simpleValueFacade).getTarget();
		componentValueFacade = NewFacadeFactory.INSTANCE.createComponent(persistentClassFacade);
		componentValueTarget = (Value)((IFacade)componentValueFacade).getTarget();
	}
	
	@Test
	public void testConstruction() {
		assertNotNull(arrayValueFacade);
		assertNotNull(arrayValueTarget);
		assertNotNull(bagValueFacade);
		assertNotNull(bagValueTarget);
		assertNotNull(listValueFacade);
		assertNotNull(listValueTarget);
		assertNotNull(manyToOneValueFacade);
		assertNotNull(manyToOneValueTarget);
		assertNotNull(mapValueFacade);
		assertNotNull(mapValueTarget);
		assertNotNull(oneToManyValueFacade);
		assertNotNull(oneToManyValueTarget);
		assertNotNull(oneToOneValueFacade);
		assertNotNull(oneToOneValueTarget);
		assertNotNull(primitiveArrayValueFacade);
		assertNotNull(primitiveArrayValueTarget);
		assertNotNull(setValueFacade);
		assertNotNull(setValueTarget);
		assertNotNull(simpleValueFacade);
		assertNotNull(simpleValueTarget);
		assertNotNull(componentValueFacade);
		assertNotNull(componentValueTarget);
	}

	@Test
	public void testIsSimpleValue() {
		assertFalse(arrayValueFacade.isSimpleValue());
		assertFalse(bagValueFacade.isSimpleValue());
		assertFalse(listValueFacade.isSimpleValue());
		assertTrue(manyToOneValueFacade.isSimpleValue());
		assertFalse(mapValueFacade.isSimpleValue());
		assertFalse(oneToManyValueFacade.isSimpleValue());
		assertTrue(oneToOneValueFacade.isSimpleValue());
		assertFalse(primitiveArrayValueFacade.isSimpleValue());
		assertFalse(setValueFacade.isSimpleValue());
		assertTrue(simpleValueFacade.isSimpleValue());
		assertTrue(componentValueFacade.isSimpleValue());
	}

	@Test
	public void testIsCollection() {
		assertTrue(arrayValueFacade.isCollection());
		assertTrue(bagValueFacade.isCollection());
		assertTrue(listValueFacade.isCollection());
		assertFalse(manyToOneValueFacade.isCollection());
		assertTrue(mapValueFacade.isCollection());
		assertFalse(oneToManyValueFacade.isCollection());
		assertFalse(oneToOneValueFacade.isCollection());
		assertTrue(primitiveArrayValueFacade.isCollection());
		assertTrue(setValueFacade.isCollection());
		assertFalse(simpleValueFacade.isCollection());
		assertFalse(componentValueFacade.isCollection());
	}
	
	@Test
	public void testGetCollectionElement() {
		assertNull(arrayValueFacade.getCollectionElement());
		((Collection)arrayValueTarget).setElement(simpleValueTarget);
		assertSame(simpleValueTarget, ((IFacade)arrayValueFacade.getCollectionElement()).getTarget());
		assertNull(bagValueFacade.getCollectionElement());
		((Collection)bagValueTarget).setElement(simpleValueTarget);
		assertSame(simpleValueTarget, ((IFacade)bagValueFacade.getCollectionElement()).getTarget());
		assertNull(listValueFacade.getCollectionElement());
		((Collection)listValueTarget).setElement(simpleValueTarget);
		assertSame(simpleValueTarget, ((IFacade)listValueFacade.getCollectionElement()).getTarget());
		assertNull(manyToOneValueFacade.getCollectionElement());
		assertNull(mapValueFacade.getCollectionElement());
		((Collection)mapValueTarget).setElement(simpleValueTarget);
		assertSame(simpleValueTarget, ((IFacade)mapValueFacade.getCollectionElement()).getTarget());
		assertNull(oneToManyValueFacade.getCollectionElement());
		assertNull(oneToOneValueFacade.getCollectionElement());
		assertNull(primitiveArrayValueFacade.getCollectionElement());
		((Collection)primitiveArrayValueTarget).setElement(simpleValueTarget);
		assertSame(simpleValueTarget, ((IFacade)primitiveArrayValueFacade.getCollectionElement()).getTarget());
		assertNull(setValueFacade.getCollectionElement());
		((Collection)setValueTarget).setElement(simpleValueTarget);
		assertSame(simpleValueTarget, ((IFacade)setValueFacade.getCollectionElement()).getTarget());
		assertNull(simpleValueFacade.getCollectionElement());
		assertNull(componentValueFacade.getCollectionElement());
	}
	
	@Test 
	public void testIsOneToMany() {
		assertFalse(arrayValueFacade.isOneToMany());
		assertFalse(bagValueFacade.isOneToMany());
		assertFalse(listValueFacade.isOneToMany());
		assertFalse(manyToOneValueFacade.isOneToMany());
		assertFalse(mapValueFacade.isOneToMany());
		assertTrue(oneToManyValueFacade.isOneToMany());
		assertFalse(oneToOneValueFacade.isOneToMany());
		assertFalse(primitiveArrayValueFacade.isOneToMany());
		assertFalse(setValueFacade.isOneToMany());
		assertFalse(simpleValueFacade.isOneToMany());
		assertFalse(componentValueFacade.isOneToMany());
	}
	
	@Test 
	public void testIsManyToOne() {
		assertFalse(arrayValueFacade.isManyToOne());
		assertFalse(bagValueFacade.isManyToOne());
		assertFalse(listValueFacade.isManyToOne());
		assertTrue(manyToOneValueFacade.isManyToOne());
		assertFalse(mapValueFacade.isManyToOne());
		assertFalse(oneToManyValueFacade.isManyToOne());
		assertFalse(oneToOneValueFacade.isManyToOne());
		assertFalse(primitiveArrayValueFacade.isManyToOne());
		assertFalse(setValueFacade.isManyToOne());
		assertFalse(simpleValueFacade.isManyToOne());
		assertFalse(componentValueFacade.isManyToOne());
	}

	@Test 
	public void testIsOneToOne() {
		assertFalse(arrayValueFacade.isOneToOne());
		assertFalse(bagValueFacade.isOneToOne());
		assertFalse(listValueFacade.isOneToOne());
		assertFalse(manyToOneValueFacade.isOneToOne());
		assertFalse(mapValueFacade.isOneToOne());
		assertFalse(oneToManyValueFacade.isOneToOne());
		assertTrue(oneToOneValueFacade.isOneToOne());
		assertFalse(primitiveArrayValueFacade.isOneToOne());
		assertFalse(setValueFacade.isOneToOne());
		assertFalse(simpleValueFacade.isOneToOne());
		assertFalse(componentValueFacade.isOneToOne());
	}

	@Test 
	public void testIsMap() {
		assertFalse(arrayValueFacade.isMap());
		assertFalse(bagValueFacade.isMap());
		assertFalse(listValueFacade.isMap());
		assertFalse(manyToOneValueFacade.isMap());
		assertTrue(mapValueFacade.isMap());
		assertFalse(oneToManyValueFacade.isMap());
		assertFalse(oneToOneValueFacade.isMap());
		assertFalse(primitiveArrayValueFacade.isMap());
		assertFalse(setValueFacade.isMap());
		assertFalse(simpleValueFacade.isMap());
		assertFalse(componentValueFacade.isMap());
	}

	@Test 
	public void testIsEmbedded() {
		assertFalse(arrayValueFacade.isEmbedded());
		assertFalse(bagValueFacade.isEmbedded());
		assertFalse(listValueFacade.isEmbedded());
		assertFalse(manyToOneValueFacade.isEmbedded());
		assertFalse(mapValueFacade.isEmbedded());
		assertFalse(oneToManyValueFacade.isEmbedded());
		assertFalse(oneToOneValueFacade.isEmbedded());
		assertFalse(primitiveArrayValueFacade.isEmbedded());
		assertFalse(setValueFacade.isEmbedded());
		assertFalse(simpleValueFacade.isEmbedded());
		assertFalse(componentValueFacade.isEmbedded());
		((Component)componentValueTarget).setEmbedded(true);
		assertTrue(componentValueFacade.isEmbedded());
	}

	@Test
	public void testIsToOne() {
		assertFalse(arrayValueFacade.isToOne());
		assertFalse(bagValueFacade.isToOne());
		assertFalse(listValueFacade.isToOne());
		assertTrue(manyToOneValueFacade.isToOne());
		assertFalse(mapValueFacade.isToOne());
		assertFalse(oneToManyValueFacade.isToOne());
		assertTrue(oneToOneValueFacade.isToOne());
		assertFalse(primitiveArrayValueFacade.isToOne());
		assertFalse(setValueFacade.isToOne());
		assertFalse(simpleValueFacade.isToOne());
		assertFalse(componentValueFacade.isToOne());
	}
	
	@Test
	public void testGetTable() {
		persistentClassFacade.setTable(null);
		assertNull(arrayValueFacade.getTable());
		persistentClassFacade.setTable(tableFacade);
		assertSame(tableTarget, ((IFacade)arrayValueFacade.getTable()).getTarget());
		persistentClassFacade.setTable(null);
		assertNull(bagValueFacade.getTable());
		persistentClassFacade.setTable(tableFacade);
		assertSame(tableTarget, ((IFacade)bagValueFacade.getTable()).getTarget());
		persistentClassFacade.setTable(null);
		assertNull(listValueFacade.getTable());
		persistentClassFacade.setTable(tableFacade);
		assertSame(tableTarget, ((IFacade)listValueFacade.getTable()).getTarget());
		((ManyToOne)manyToOneValueTarget).setTable(null);
		assertNull(manyToOneValueFacade.getTable());
		((ManyToOne)manyToOneValueTarget).setTable(tableTarget);
		assertSame(tableTarget, ((IFacade)manyToOneValueFacade.getTable()).getTarget());
		persistentClassFacade.setTable(null);
		assertNull(mapValueFacade.getTable());
		persistentClassFacade.setTable(tableFacade);
		assertSame(tableTarget, ((IFacade)mapValueFacade.getTable()).getTarget());
		assertNull(oneToManyValueFacade.getTable());
		persistentClassFacade.setTable(tableFacade);
		oneToManyValueFacade = NewFacadeFactory.INSTANCE.createOneToMany(persistentClassFacade);
		assertSame(tableTarget, ((IFacade)oneToManyValueFacade.getTable()).getTarget());
		assertNull(oneToOneValueFacade.getTable());
		persistentClassFacade.setTable(tableFacade);
		oneToOneValueFacade = NewFacadeFactory.INSTANCE.createOneToOne(persistentClassFacade);
		assertSame(tableTarget, ((IFacade)oneToOneValueFacade.getTable()).getTarget());
		persistentClassFacade.setTable(null);
		assertNull(primitiveArrayValueFacade.getTable());
		persistentClassFacade.setTable(tableFacade);
		assertSame(tableTarget, ((IFacade)primitiveArrayValueFacade.getTable()).getTarget());
		persistentClassFacade.setTable(null);
		assertNull(setValueFacade.getTable());
		persistentClassFacade.setTable(tableFacade);
		assertSame(tableTarget, ((IFacade)setValueFacade.getTable()).getTarget());
		((SimpleValue)simpleValueTarget).setTable(null);
		assertNull(simpleValueFacade.getTable());
		((SimpleValue)simpleValueTarget).setTable(tableTarget);
		assertSame(tableTarget, ((IFacade)simpleValueFacade.getTable()).getTarget());
		((Component)componentValueTarget).setTable(null);
		assertNull(componentValueFacade.getTable());
		((Component)componentValueTarget).setTable(tableTarget);
		assertSame(tableTarget, ((IFacade)componentValueFacade.getTable()).getTarget());
	}

	@Test
	public void testGetType() {
		((SimpleValue)simpleValueTarget).setTypeName("java.lang.Integer");
		IType typeFacade = simpleValueFacade.getType();
		assertTrue((Type)((IFacade)typeFacade).getTarget() instanceof BasicType);
		((Collection)arrayValueTarget).setElement(simpleValueTarget);
		typeFacade = arrayValueFacade.getType();
		assertTrue((Type)((IFacade)typeFacade).getTarget() instanceof ArrayType);
		((Collection)bagValueTarget).setElement(simpleValueTarget);
		typeFacade = bagValueFacade.getType();
		assertTrue((Type)((IFacade)typeFacade).getTarget() instanceof BagType);
		((Collection)listValueTarget).setElement(simpleValueTarget);
		typeFacade = listValueFacade.getType();
		assertTrue((Type)((IFacade)typeFacade).getTarget() instanceof ListType);
		typeFacade = manyToOneValueFacade.getType();
		assertTrue((Type)((IFacade)typeFacade).getTarget() instanceof ManyToOneType);
		((Collection)mapValueTarget).setElement(simpleValueTarget);
		typeFacade = mapValueFacade.getType();
		assertTrue((Type)((IFacade)typeFacade).getTarget() instanceof MapType);
		typeFacade = oneToManyValueFacade.getType();
		assertTrue((Type)((IFacade)typeFacade).getTarget() instanceof ManyToOneType);
		typeFacade = oneToOneValueFacade.getType();
		assertTrue((Type)((IFacade)typeFacade).getTarget() instanceof OneToOneType);
		((Collection)primitiveArrayValueTarget).setElement(simpleValueTarget);
		typeFacade = primitiveArrayValueFacade.getType();
		assertTrue((Type)((IFacade)typeFacade).getTarget() instanceof ArrayType);
		((Collection)setValueTarget).setElement(simpleValueTarget);
		typeFacade = setValueFacade.getType();
		assertTrue((Type)((IFacade)typeFacade).getTarget() instanceof SetType);
		((Component)componentValueTarget).setComponentClassName("java.lang.String");
		typeFacade = componentValueFacade.getType();
		assertTrue((Type)((IFacade)typeFacade).getTarget() instanceof ComponentType);
	}
	
	@Test
	public void testSetElement() {
		assertNull(((Collection)arrayValueTarget).getElement());
		arrayValueFacade.setElement(simpleValueFacade);
		assertSame(simpleValueTarget, ((Collection)arrayValueTarget).getElement());
		assertNull(((Collection)bagValueTarget).getElement());
		bagValueFacade.setElement(simpleValueFacade);
		assertSame(simpleValueTarget, ((Collection)bagValueTarget).getElement());
		assertNull(((Collection)listValueTarget).getElement());
		listValueFacade.setElement(simpleValueFacade);
		assertSame(simpleValueTarget, ((Collection)listValueTarget).getElement());
		assertNull(manyToOneValueFacade.getElement());
		manyToOneValueFacade.setElement(simpleValueFacade);
		assertNull(manyToOneValueFacade.getElement());
		assertNull(((Collection)mapValueTarget).getElement());
		mapValueFacade.setElement(simpleValueFacade);
		assertSame(simpleValueTarget, ((Collection)mapValueTarget).getElement());
		assertNull(oneToManyValueFacade.getElement());
		oneToManyValueFacade.setElement(simpleValueFacade);
		assertNull(oneToManyValueFacade.getElement());
		assertNull(oneToOneValueFacade.getElement());
		oneToOneValueFacade.setElement(simpleValueFacade);
		assertNull(oneToOneValueFacade.getElement());
		assertNull(((Collection)primitiveArrayValueTarget).getElement());
		primitiveArrayValueFacade.setElement(simpleValueFacade);
		assertSame(simpleValueTarget, ((Collection)primitiveArrayValueTarget).getElement());
		assertNull(((Collection)setValueTarget).getElement());
		setValueFacade.setElement(simpleValueFacade);
		assertSame(simpleValueTarget, ((Collection)setValueTarget).getElement());
		assertNull(simpleValueFacade.getElement());
		simpleValueFacade.setElement(arrayValueFacade);
		assertNull(simpleValueFacade.getElement());
		assertNull(componentValueFacade.getElement());
		componentValueFacade.setElement(arrayValueFacade);
		assertNull(componentValueFacade.getElement());
	}
	
	
	
	
	
	
	@Test
	public void testGetCollectionTable() {
		assertNull(arrayValueFacade.getCollectionTable());
		((Collection)arrayValueTarget).setCollectionTable(tableTarget);
		assertSame(tableTarget, ((IFacade)arrayValueFacade.getCollectionTable()).getTarget());
		assertNull(bagValueFacade.getCollectionTable());
		((Collection)bagValueTarget).setCollectionTable(tableTarget);
		assertSame(tableTarget, ((IFacade)bagValueFacade.getCollectionTable()).getTarget());
		assertNull(listValueFacade.getCollectionTable());
		((Collection)listValueTarget).setCollectionTable(tableTarget);
		assertSame(tableTarget, ((IFacade)listValueFacade.getCollectionTable()).getTarget());
		assertNull(manyToOneValueFacade.getCollectionTable());
		assertNull(mapValueFacade.getCollectionTable());
		((Collection)mapValueTarget).setCollectionTable(tableTarget);
		assertSame(tableTarget, ((IFacade)mapValueFacade.getCollectionTable()).getTarget());
		assertNull(oneToManyValueFacade.getCollectionTable());
		assertNull(oneToOneValueFacade.getCollectionTable());
		assertNull(primitiveArrayValueFacade.getCollectionTable());
		((Collection)primitiveArrayValueTarget).setCollectionTable(tableTarget);
		assertSame(tableTarget, ((IFacade)primitiveArrayValueFacade.getCollectionTable()).getTarget());
		assertNull(setValueFacade.getCollectionTable());
		((Collection)setValueTarget).setCollectionTable(tableTarget);
		assertSame(tableTarget, ((IFacade)setValueFacade.getCollectionTable()).getTarget());
		assertNull(simpleValueFacade.getCollectionTable());
		assertNull(componentValueFacade.getCollectionTable());
	}
	
	
	
	
	

	@Test
	public void testGetElement() {
		assertNull(arrayValueFacade.getElement());
		((Collection)arrayValueTarget).setElement(simpleValueTarget);
		assertSame(simpleValueTarget, ((IFacade)arrayValueFacade.getElement()).getTarget());
		assertNull(bagValueFacade.getElement());
		((Collection)bagValueTarget).setElement(simpleValueTarget);
		assertSame(simpleValueTarget, ((IFacade)bagValueFacade.getElement()).getTarget());
		assertNull(listValueFacade.getElement());
		((Collection)listValueTarget).setElement(simpleValueTarget);
		assertSame(simpleValueTarget, ((IFacade)listValueFacade.getElement()).getTarget());
		assertNull(manyToOneValueFacade.getElement());
		assertNull(mapValueFacade.getElement());
		((Collection)mapValueTarget).setElement(simpleValueTarget);
		assertSame(simpleValueTarget, ((IFacade)mapValueFacade.getElement()).getTarget());
		assertNull(oneToManyValueFacade.getElement());
		assertNull(oneToOneValueFacade.getElement());
		assertNull(primitiveArrayValueFacade.getElement());
		((Collection)primitiveArrayValueTarget).setElement(simpleValueTarget);
		assertSame(simpleValueTarget, ((IFacade)primitiveArrayValueFacade.getElement()).getTarget());
		assertNull(setValueFacade.getElement());
		((Collection)setValueTarget).setElement(simpleValueTarget);
		assertSame(simpleValueTarget, ((IFacade)setValueFacade.getElement()).getTarget());
		assertNull(simpleValueFacade.getElement());
		assertNull(componentValueFacade.getElement());
	}
	
	

}