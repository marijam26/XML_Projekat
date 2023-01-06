//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.12 at 03:44:33 PM CET 
//


package project.z1.model.z1;

import project.z1.model.main_schema.TPrilog;

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
 *         &lt;element name="Primerak_znaka" type="{http://ftn.uns.ac.rs/sema}TPrilog"/>
 *         &lt;element name="Spisak_robe" type="{http://ftn.uns.ac.rs/sema}TPrilog"/>
 *         &lt;element name="Punomoc" type="{http://ftn.uns.ac.rs/sema}TPrilog"/>
 *         &lt;element name="Ranija_punomoc" type="{http://ftn.uns.ac.rs/sema}TPrilog"/>
 *         &lt;element name="Naknadna_punomoc" type="{http://ftn.uns.ac.rs/sema}TPrilog"/>
 *         &lt;element name="Opsti_akt" type="{http://ftn.uns.ac.rs/sema}TPrilog"/>
 *         &lt;element name="Dokaz_o_pravu_prvenstva" type="{http://ftn.uns.ac.rs/sema}TPrilog"/>
 *         &lt;element name="Dokaz_o_uplati" type="{http://ftn.uns.ac.rs/sema}TPrilog"/>
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
    "primerakZnaka",
    "spisakRobe",
    "punomoc",
    "ranijaPunomoc",
    "naknadnaPunomoc",
    "opstiAkt",
    "dokazOPravuPrvenstva",
    "dokazOUplati"
})
@XmlRootElement(name = "Prilozi")
public class Prilozi {

    @XmlElement(name = "Primerak_znaka", required = true)
    protected TPrilog primerakZnaka;
    @XmlElement(name = "Spisak_robe", required = true)
    protected TPrilog spisakRobe;
    @XmlElement(name = "Punomoc", required = true)
    protected TPrilog punomoc;
    @XmlElement(name = "Ranija_punomoc", required = true)
    protected TPrilog ranijaPunomoc;
    @XmlElement(name = "Naknadna_punomoc", required = true)
    protected TPrilog naknadnaPunomoc;
    @XmlElement(name = "Opsti_akt", required = true)
    protected TPrilog opstiAkt;
    @XmlElement(name = "Dokaz_o_pravu_prvenstva", required = true)
    protected TPrilog dokazOPravuPrvenstva;
    @XmlElement(name = "Dokaz_o_uplati", required = true)
    protected TPrilog dokazOUplati;

    /**
     * Gets the value of the primerakZnaka property.
     * 
     * @return
     *     possible object is
     *     {@link TPrilog }
     *     
     */
    public TPrilog getPrimerakZnaka() {
        return primerakZnaka;
    }

    /**
     * Sets the value of the primerakZnaka property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPrilog }
     *     
     */
    public void setPrimerakZnaka(TPrilog value) {
        this.primerakZnaka = value;
    }

    /**
     * Gets the value of the spisakRobe property.
     * 
     * @return
     *     possible object is
     *     {@link TPrilog }
     *     
     */
    public TPrilog getSpisakRobe() {
        return spisakRobe;
    }

    /**
     * Sets the value of the spisakRobe property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPrilog }
     *     
     */
    public void setSpisakRobe(TPrilog value) {
        this.spisakRobe = value;
    }

    /**
     * Gets the value of the punomoc property.
     * 
     * @return
     *     possible object is
     *     {@link TPrilog }
     *     
     */
    public TPrilog getPunomoc() {
        return punomoc;
    }

    /**
     * Sets the value of the punomoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPrilog }
     *     
     */
    public void setPunomoc(TPrilog value) {
        this.punomoc = value;
    }

    /**
     * Gets the value of the ranijaPunomoc property.
     * 
     * @return
     *     possible object is
     *     {@link TPrilog }
     *     
     */
    public TPrilog getRanijaPunomoc() {
        return ranijaPunomoc;
    }

    /**
     * Sets the value of the ranijaPunomoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPrilog }
     *     
     */
    public void setRanijaPunomoc(TPrilog value) {
        this.ranijaPunomoc = value;
    }

    /**
     * Gets the value of the naknadnaPunomoc property.
     * 
     * @return
     *     possible object is
     *     {@link TPrilog }
     *     
     */
    public TPrilog getNaknadnaPunomoc() {
        return naknadnaPunomoc;
    }

    /**
     * Sets the value of the naknadnaPunomoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPrilog }
     *     
     */
    public void setNaknadnaPunomoc(TPrilog value) {
        this.naknadnaPunomoc = value;
    }

    /**
     * Gets the value of the opstiAkt property.
     * 
     * @return
     *     possible object is
     *     {@link TPrilog }
     *     
     */
    public TPrilog getOpstiAkt() {
        return opstiAkt;
    }

    /**
     * Sets the value of the opstiAkt property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPrilog }
     *     
     */
    public void setOpstiAkt(TPrilog value) {
        this.opstiAkt = value;
    }

    /**
     * Gets the value of the dokazOPravuPrvenstva property.
     * 
     * @return
     *     possible object is
     *     {@link TPrilog }
     *     
     */
    public TPrilog getDokazOPravuPrvenstva() {
        return dokazOPravuPrvenstva;
    }

    /**
     * Sets the value of the dokazOPravuPrvenstva property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPrilog }
     *     
     */
    public void setDokazOPravuPrvenstva(TPrilog value) {
        this.dokazOPravuPrvenstva = value;
    }

    /**
     * Gets the value of the dokazOUplati property.
     * 
     * @return
     *     possible object is
     *     {@link TPrilog }
     *     
     */
    public TPrilog getDokazOUplati() {
        return dokazOUplati;
    }

    /**
     * Sets the value of the dokazOUplati property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPrilog }
     *     
     */
    public void setDokazOUplati(TPrilog value) {
        this.dokazOUplati = value;
    }

}
