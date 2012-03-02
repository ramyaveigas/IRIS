package com.temenos.interaction.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.odata4j.edm.EdmDataServices;
import org.odata4j.producer.exceptions.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A MetaDataResource is resource that describes another resource.
 * @author aphethean
 */
@XmlRootElement(name = "metadata")
@XmlAccessorType(XmlAccessType.FIELD)
public class MetaDataResource implements RESTResource {
	@XmlTransient
	private final Logger logger = LoggerFactory.getLogger(MetaDataResource.class);

	@XmlAnyElement(lax=true)
	private Object metadata; 

	@XmlTransient
	private EdmDataServices edmx; 
	
	public MetaDataResource(Object metadata) {
		this.metadata = metadata;
	}
	
	public MetaDataResource(EdmDataServices edmx) {
		this.edmx = edmx;
	}
	
	public Object getMetadata() {
		return metadata;
	}
	
	public EdmDataServices getEdmx() {
		if (edmx != null) return edmx;
		if (metadata != null) {
			logger.debug("Discovered a jaxb / json deserialised object");
			// TODO implement a generic jaxb to OEntity conversion for our 'entity'
			/*
			 * TODO implement a generic jaxb to OEntities conversion for our 
			 * 'entities' or throw an error to change the runtime configuration 
			 * to represent this object with a JAXB Provider
			 */
			throw new NotImplementedException();
		}
		throw new RuntimeException("Metadata has not been be supplied");
	}
	
}
