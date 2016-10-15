package org.jboss.tools.hibernate.runtime.v_5_0.internal;

import java.io.File;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Set;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.jaxb.spi.Binding;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.DefaultNamingStrategy;
import org.hibernate.dialect.Dialect;
import org.hibernate.mapping.Collection;
import org.hibernate.mapping.OneToMany;
import org.hibernate.tool.util.MetadataHelper;
import org.jboss.tools.hibernate.runtime.common.IFacade;
import org.jboss.tools.hibernate.runtime.common.IFacadeFactory;
import org.jboss.tools.hibernate.runtime.spi.IConfiguration;
import org.jboss.tools.hibernate.runtime.spi.IDialect;
import org.jboss.tools.hibernate.runtime.spi.IMappings;
import org.jboss.tools.hibernate.runtime.spi.INamingStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.EntityResolver;
import org.xml.sax.helpers.DefaultHandler;

public class ConfigurationFacadeTest2 {
	
	private static final String FOO_HBM_XML_STRING =
			"<!DOCTYPE hibernate-mapping PUBLIC" +
			"		'-//Hibernate/Hibernate Mapping DTD 3.0//EN'" +
			"		'http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd'>" +
			"<hibernate-mapping package='org.jboss.tools.hibernate.runtime.v_5_0.internal'>" +
			"  <class name='ConfigurationFacadeTest2$Foo'>" + 
			"    <id name='fooId'/>" +
			"  </class>" +
			"</hibernate-mapping>";
	
	private static final String BAR_HBM_XML_STRING =
			"<!DOCTYPE hibernate-mapping PUBLIC" +
			"		'-//Hibernate/Hibernate Mapping DTD 3.0//EN'" +
			"		'http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd'>" +
			"<hibernate-mapping package='org.jboss.tools.hibernate.runtime.v_5_0.internal'>" +
			"  <class name='ConfigurationFacadeTest2$Bar'>" + 
			"    <id name='barId'/>" +
			"    <set name='fooSet' inverse='true'>" +
			"      <key column='fooId'/>" +
			"      <one-to-many class='ConfigurationFacadeTest2$Foo'/>" +
			"    </set>" +
			"  </class>" +
			"</hibernate-mapping>";
	
	static class Foo {
		public String fooId;
	}
	
	static class Bar {
		public String barId;
		public Set<Foo> fooSet;
	}
			
	private static final IFacadeFactory FACADE_FACTORY = new FacadeFactoryImpl();

	private IConfiguration configurationFacade = null;
	private Configuration configuration = null;

	@Before
	public void setUp() {
		configuration = new Configuration();
		configurationFacade = FACADE_FACTORY.createConfiguration(configuration);
	}
	
	@Test
	public void testGetProperty() {
		Assert.assertNull(configurationFacade.getProperty("foo"));
		configuration.setProperty("foo", "bar");
		Assert.assertEquals("bar", configurationFacade.getProperty("foo"));
	}

	@Test 
	public void testSetProperties() {
		Properties testProperties = new Properties();
		Assert.assertNotSame(testProperties, configuration.getProperties());
		Assert.assertSame(
				configurationFacade, 
				configurationFacade.setProperties(testProperties));
		Assert.assertSame(testProperties, configuration.getProperties());
	}
	
	@Test
	public void testAddFile() throws Exception {
		File testFile = File.createTempFile("test", "hbm.xml");
		PrintWriter printWriter = new PrintWriter(testFile);
		printWriter.write(FOO_HBM_XML_STRING);
		printWriter.close();
		MetadataSources metadataSources = MetadataHelper.getMetadataSources(configuration);
		Assert.assertTrue(metadataSources.getXmlBindings().isEmpty());
		Assert.assertSame(
				configurationFacade,
				configurationFacade.addFile(testFile));
		Assert.assertFalse(metadataSources.getXmlBindings().isEmpty());
		Binding<?> binding = metadataSources.getXmlBindings().iterator().next();
		Assert.assertEquals(testFile.getAbsolutePath(), binding.getOrigin().getName());
		Assert.assertTrue(testFile.delete());
	}
	
	@Test
	public void testSetEntityResolver() {
		EntityResolver testResolver = new DefaultHandler();
		ConfigurationFacadeImpl facade = (ConfigurationFacadeImpl)configurationFacade;
		Assert.assertNull(facade.entityResolver);
		configurationFacade.setEntityResolver(testResolver);
		Assert.assertSame(testResolver, facade.entityResolver);
	}
	
	@Test
	public void testGetEntityResolver() {
		EntityResolver testResolver = new DefaultHandler();
		ConfigurationFacadeImpl facade = (ConfigurationFacadeImpl)configurationFacade;
		Assert.assertNotSame(testResolver, configurationFacade.getEntityResolver());
		facade.entityResolver = testResolver;
		Assert.assertSame(testResolver, configurationFacade.getEntityResolver());
	}
	
	@Test
	public void testSetNamingStrategy() {
		DefaultNamingStrategy dns = new DefaultNamingStrategy();
		INamingStrategy namingStrategy = FACADE_FACTORY.createNamingStrategy(dns);
		ConfigurationFacadeImpl facade = (ConfigurationFacadeImpl)configurationFacade;
		Assert.assertNotSame(dns, facade.namingStrategy);
		configurationFacade.setNamingStrategy(namingStrategy);
		Assert.assertSame(dns, facade.namingStrategy);
	}
	
	@Test
	public void testAddProperties() {
		Assert.assertNull(configuration.getProperty("foo"));
		Properties testProperties = new Properties();
		testProperties.put("foo", "bar");
		configurationFacade.addProperties(testProperties);
		Assert.assertEquals("bar", configuration.getProperty("foo"));
	}
	
	@Test
	public void testConfigure() {
		String fooClassName = 
				"org.jboss.tools.hibernate.runtime.v_5_0.internal.test.Foo";
		Metadata metadata = MetadataHelper.getMetadata(configuration);
		Assert.assertNull(metadata.getEntityBinding(fooClassName));
		configurationFacade.configure();
		metadata = MetadataHelper.getMetadata(configuration);
		Assert.assertNotNull(metadata.getEntityBinding(fooClassName));
	}
	
	@Test 
	public void testCreateMappings() {
		IMappings mappingsFacade = configurationFacade.createMappings();
		Assert.assertNotNull(mappingsFacade);
		Object object = ((IFacade)mappingsFacade).getTarget();
		Assert.assertNull(object);
	}

	@Test
	public void testBuildMappings() throws Exception {
		File fooFile = File.createTempFile("foo", "hbm.xml");
		PrintWriter fooWriter = new PrintWriter(fooFile);
		fooWriter.write(FOO_HBM_XML_STRING);
		fooWriter.close();
		configuration.addFile(fooFile);
		File barFile = File.createTempFile("bar", "hbm.xml");
		PrintWriter barWriter = new PrintWriter(barFile);
		barWriter.write(BAR_HBM_XML_STRING);
		barWriter.close();
		configuration.addFile(barFile);
		ConfigurationFacadeImpl facade = (ConfigurationFacadeImpl)configurationFacade;
		Assert.assertNull(facade.mappings);
		Assert.assertNull(facade.metadata);
		configurationFacade.buildMappings();
		Assert.assertNotNull(facade.mappings);
		String collectionName = 
				"org.jboss.tools.hibernate.runtime.v_5_0.internal.ConfigurationFacadeTest2$Bar.fooSet";
		Collection collection = facade.metadata.getCollectionBinding(collectionName);
		OneToMany element = (OneToMany)collection.getElement();
		Assert.assertEquals(
				"org.jboss.tools.hibernate.runtime.v_5_0.internal.ConfigurationFacadeTest2$Foo",
				element.getAssociatedClass().getClassName());
	}
	
	@Test
	public void testGetDialect() {
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		IDialect dialectFacade = configurationFacade.getDialect();
		Assert.assertNotNull(dialectFacade);
		Dialect dialect = (Dialect)((IFacade)dialectFacade).getTarget();
		Assert.assertEquals("org.hibernate.dialect.H2Dialect", dialect.getClass().getName());
	}
	
}
