//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.12 at 03:42:25 PM CET 
//


package project.xml.a1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


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
 *         &lt;element name="Nacin_koriscenja" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Vrsta">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://ftn.uns.ac.rs/autorska_dela>TVrsta">
 *                 &lt;attribute name="Ostala_vrsta_dela" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Format_zapisa">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://ftn.uns.ac.rs/autorska_dela>TFormat_zapisa">
 *                 &lt;attribute name="Ostali_format" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://ftn.uns.ac.rs/autorska_dela}Izvorno_delo" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Stvoreno_u_radnom_odnosu" type="{http://www.w3.org/2001/XMLSchema}boolean" />
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
    "nacinKoriscenja",
    "vrsta",
    "formatZapisa",
    "izvornoDelo"
})
@XmlRootElement(name = "Autorsko_delo")
public class AutorskoDelo {

    @XmlElement(name = "Naslov", required = true)
    protected String naslov;
    @XmlElement(name = "Nacin_koriscenja", required = true)
    protected String nacinKoriscenja;
    @XmlElement(name = "Vrsta", required = true)
    protected AutorskoDelo.Vrsta vrsta;
    @XmlElement(name = "Format_zapisa", required = true)
    protected AutorskoDelo.FormatZapisa formatZapisa;
    @XmlElement(name = "Izvorno_delo")
    protected IzvornoDelo izvornoDelo;
    @XmlAttribute(name = "Stvoreno_u_radnom_odnosu")
    protected Boolean stvorenoURadnomOdnosu;

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
     * Gets the value of the nacinKoriscenja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacinKoriscenja() {
        return nacinKoriscenja;
    }

    /**
     * Sets the value of the nacinKoriscenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacinKoriscenja(String value) {
        this.nacinKoriscenja = value;
    }

    /**
     * Gets the value of the vrsta property.
     * 
     * @return
     *     possible object is
     *     {@link AutorskoDelo.Vrsta }
     *     
     */
    public AutorskoDelo.Vrsta getVrsta() {
        return vrsta;
    }

    /**
     * Sets the value of the vrsta property.
     * 
     * @param value
     *     allowed object is
     *     {@link AutorskoDelo.Vrsta }
     *     
     */
    public void setVrsta(AutorskoDelo.Vrsta value) {
        this.vrsta = value;
    }

    /**
     * Gets the value of the formatZapisa property.
     * 
     * @return
     *     possible object is
     *     {@link AutorskoDelo.FormatZapisa }
     *     
     */
    public AutorskoDelo.FormatZapisa getFormatZapisa() {
        return formatZapisa;
    }

    /**
     * Sets the value of the formatZapisa property.
     * 
     * @param value
     *     allowed object is
     *     {@link AutorskoDelo.FormatZapisa }
     *     
     */
    public void setFormatZapisa(AutorskoDelo.FormatZapisa value) {
        this.formatZapisa = value;
    }

    /**
     * Gets the value of the izvornoDelo property.
     * 
     * @return
     *     possible object is
     *     {@link IzvornoDelo }
     *     
     */
    public IzvornoDelo getIzvornoDelo() {
        return izvornoDelo;
    }

    /**
     * Sets the value of the izvornoDelo property.
     * 
     * @param value
     *     allowed object is
     *     {@link IzvornoDelo }
     *     
     */
    public void setIzvornoDelo(IzvornoDelo value) {
        this.izvornoDelo = value;
    }

    /**
     * Gets the value of the stvorenoURadnomOdnosu property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isStvorenoURadnomOdnosu() {
        return stvorenoURadnomOdnosu;
    }

    /**
     * Sets the value of the stvorenoURadnomOdnosu property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setStvorenoURadnomOdnosu(Boolean value) {
        this.stvorenoURadnomOdnosu = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://ftn.uns.ac.rs/autorska_dela>TFormat_zapisa">
     *       &lt;attribute name="Ostali_format" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class FormatZapisa {

        @XmlValue
        protected TFormatZapisa value;
        @XmlAttribute(name = "Ostali_format")
        protected String ostaliFormat;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link TFormatZapisa }
         *     
         */
        public TFormatZapisa getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link TFormatZapisa }
         *     
         */
        public void setValue(TFormatZapisa value) {
            this.value = value;
        }

        /**
         * Gets the value of the ostaliFormat property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOstaliFormat() {
            return ostaliFormat;
        }

        /**
         * Sets the value of the ostaliFormat property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOstaliFormat(String value) {
            this.ostaliFormat = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://ftn.uns.ac.rs/autorska_dela>TVrsta">
     *       &lt;attribute name="Ostala_vrsta_dela" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Vrsta {

        @XmlValue
        protected TVrsta value;
        @XmlAttribute(name = "Ostala_vrsta_dela")
        protected String ostalaVrstaDela;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link TVrsta }
         *     
         */
        public TVrsta getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link TVrsta }
         *     
         */
        public void setValue(TVrsta value) {
            this.value = value;
        }

        /**
         * Gets the value of the ostalaVrstaDela property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOstalaVrstaDela() {
            return ostalaVrstaDela;
        }

        /**
         * Sets the value of the ostalaVrstaDela property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOstalaVrstaDela(String value) {
            this.ostalaVrstaDela = value;
        }

    }

}