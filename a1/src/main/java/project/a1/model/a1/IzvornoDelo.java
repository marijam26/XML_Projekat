//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.12 at 03:42:25 PM CET 
//


package project.a1.model.a1;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Naslov" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;choice>
 *           &lt;element name="Autor" type="{http://ftn.uns.ac.rs/autorska_dela}TAutor"/>
 *           &lt;element name="Anoniman_autor" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "naslov",
    "autor",
    "anonimanAutor"
})
@XmlRootElement(name = "Izvorno_delo")
public class IzvornoDelo {

    @XmlElement(name = "Naslov", required = true)
    protected String naslov;
    @XmlElement(name = "Autor")
    protected TAutor autor;
    @XmlElement(name = "Anoniman_autor", defaultValue = "true")
    protected Boolean anonimanAutor;

    /**
     * Gets the value of the naslov property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaslov() {
        return naslov;
    }

    /**
     * Sets the value of the naslov property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaslov(String value) {
        this.naslov = value;
    }

    /**
     * Gets the value of the autor property.
     * 
     * @return
     *     possible object is
     *     {@link TAutor }
     *     
     */
    public TAutor getAutor() {
        return autor;
    }

    /**
     * Sets the value of the autor property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAutor }
     *     
     */
    public void setAutor(TAutor value) {
        this.autor = value;
    }

    /**
     * Gets the value of the anonimanAutor property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAnonimanAutor() {
        return anonimanAutor;
    }

    /**
     * Sets the value of the anonimanAutor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAnonimanAutor(Boolean value) {
        this.anonimanAutor = value;
    }

}
