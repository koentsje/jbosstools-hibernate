package org.jboss.tools.hibernate.runtime.v_5_3;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ExportersPresenceTest {
	
	@Test
	public void testDdlExporter() {
		try {
			ClassLoader cl = getClass().getClassLoader();
			Class<?> ddlExporterClass = cl.loadClass("org.hibernate.tool.hbm2x.Hbm2DDLExporter");
			assertNotNull(ddlExporterClass);
		} catch (Throwable t) {
			fail(t);
		}
	}

	@Test
	public void testPojoExporter() {
		try {
			ClassLoader cl = getClass().getClassLoader();
			Class<?> pojoExporterClass = cl.loadClass("org.hibernate.tool.hbm2x.POJOExporter");
			assertNotNull(pojoExporterClass);
		} catch (Throwable t) {
			fail(t);
		}
	}

	@Test
	public void testHibernateMappingExporter() {
		try {
			ClassLoader cl = getClass().getClassLoader();
			Class<?> hibernateMappingExporterClass = cl.loadClass("org.hibernate.tool.hbm2x.HibernateMappingExporter");
			assertNotNull(hibernateMappingExporterClass);
		} catch (Throwable t) {
			fail(t);
		}
	}

	@Test
	public void testDAOExporter() {
		try {
			ClassLoader cl = getClass().getClassLoader();
			Class<?> daoExporterClass = cl.loadClass("org.hibernate.tool.hbm2x.DAOExporter");
			assertNotNull(daoExporterClass);
		} catch (Throwable t) {
			fail(t);
		}
	}

	@Test
	public void testGenericExporter() {
		try {
			ClassLoader cl = getClass().getClassLoader();
			Class<?> genericExporterClass = cl.loadClass("org.hibernate.tool.hbm2x.GenericExporter");
			assertNotNull(genericExporterClass);
		} catch (Throwable t) {
			fail(t);
		}
	}

	@Test
	public void testHibernateConfigurationExporter() {
		try {
			ClassLoader cl = getClass().getClassLoader();
			Class<?> hibernateConfigurationExporterClass = cl.loadClass("org.hibernate.tool.hbm2x.HibernateConfigurationExporter");
			assertNotNull(hibernateConfigurationExporterClass);
		} catch (Throwable t) {
			fail(t);
		}
	}

	@Test
	public void testDocExporter() {
		try {
			ClassLoader cl = getClass().getClassLoader();
			Class<?> docExporterClass = cl.loadClass("org.hibernate.tool.hbm2x.DocExporter");
			assertNotNull(docExporterClass);
		} catch (Throwable t) {
			fail(t);
		}
	}

}
