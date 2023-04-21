package org.jboss.tools.hibernate.orm.runtime.exp.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.hibernate.mapping.Column;
import org.hibernate.mapping.PrimaryKey;
import org.hibernate.mapping.Table;
import org.hibernate.tool.orm.jbt.wrp.ColumnWrapper;
import org.jboss.tools.hibernate.orm.runtime.exp.internal.util.GenericFacadeFactory;
import org.jboss.tools.hibernate.runtime.common.IFacade;
import org.jboss.tools.hibernate.runtime.spi.IColumn;
import org.jboss.tools.hibernate.runtime.spi.IPrimaryKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IPrimaryKeyTest {

	private IPrimaryKey primaryKeyFacade = null; 
	private PrimaryKey primaryKeyTarget = null;
	
	@BeforeEach
	public void beforeEach() {
		primaryKeyTarget = new PrimaryKey(new Table(""));
		primaryKeyFacade = (IPrimaryKey)GenericFacadeFactory.createFacade(
				IPrimaryKey.class, 
				primaryKeyTarget);
	}
	
	@Test
	public void testConstruction() {
		assertNotNull(primaryKeyFacade);
		assertNotNull(primaryKeyTarget);
	}

	@Test
	public void testAddColumn() throws Exception {
		Column columnTarget = new ColumnWrapper("foo");
		IColumn columnFacade = (IColumn)GenericFacadeFactory.createFacade(IColumn.class, columnTarget);
		assertTrue(primaryKeyTarget.getColumns().isEmpty());
		primaryKeyFacade.addColumn(columnFacade);
		assertEquals(1, primaryKeyTarget.getColumns().size());
		assertSame(columnTarget, primaryKeyTarget.getColumns().get(0));
	}
	
	@Test
	public void testGetColumnSpan() {
		assertEquals(0, primaryKeyFacade.getColumnSpan());
		primaryKeyTarget.addColumn(new Column());
		assertEquals(1, primaryKeyFacade.getColumnSpan());
	}
	
	@Test
	public void testGetColumns() throws Exception {
		Column columnTarget = new ColumnWrapper("foo");
		assertTrue(primaryKeyFacade.getColumns().isEmpty());
		primaryKeyTarget.addColumn(columnTarget);
		List<IColumn> columnFacades = primaryKeyFacade.getColumns();
		assertNotNull(columnFacades);
		assertEquals(1, columnFacades.size());
		assertSame(columnTarget, ((IFacade)columnFacades.get(0)).getTarget());
	}
	
}
