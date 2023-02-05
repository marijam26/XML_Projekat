<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
                xmlns:a1="http://ftn.uns.ac.rs/a"
                xmlns:pred="http://ftn.uns.ac.rs/a/pred/"
                xmlns:sema="http://ftn.uns.ac.rs/sema"
>
    <xsl:template match="/">
        <rdf:RDF>
            <xsl:variable name="Zahtev_za_autorsko_delo">A-<xsl:value-of select="//a1:Zahtev_za_autorska_dela/@Broj"/></xsl:variable>
            <rdf:Description rdf:about="{$Zahtev_za_autorsko_delo}">
                <pred:Broj>
                    <xsl:value-of select="//a1:Zahtev_za_autorska_dela/@Broj"/>
                </pred:Broj>

                <pred:Datum_podnosenja>
                    <xsl:value-of select="//a1:Zahtev_za_autorska_dela/@Datum_podnosenja"/>
                </pred:Datum_podnosenja>

                <xsl:choose>
                    <xsl:when test="//sema:Podnosilac_prijave/sema:Poslovno_ime">
                        <pred:Podnosilac_poslovno_ime>
                            <xsl:value-of select="//sema:Podnosilac_prijave/sema:Poslovno_ime"/>
                        </pred:Podnosilac_poslovno_ime>
                    </xsl:when>
                    <xsl:otherwise>
                        <pred:Podnosilac_ime>
                            <xsl:value-of select="//sema:Podnosilac_prijave/sema:Ime"/>
                        </pred:Podnosilac_ime>
                        <pred:Podnosilac_prezime>
                            <xsl:value-of select="//sema:Podnosilac_prijave/sema:Prezime"/>
                        </pred:Podnosilac_prezime>
                    </xsl:otherwise>
                </xsl:choose>


                <pred:Podnosilac_eposta>
                    <xsl:value-of select="//sema:Podnosilac_prijave/sema:Kontakt/sema:E_posta"/>
                </pred:Podnosilac_eposta>

                <pred:Naslov_autorskog_dela>
                    <xsl:value-of select="//a1:Autorsko_delo/a1:Naslov"/>
                </pred:Naslov_autorskog_dela>
                
                <pred:Vrsta_autorskog_dela>
                    <xsl:variable name="vrsta" select="a1:Zahtev_za_autorska_dela/a1:Autorsko_delo/a1:Vrsta"/>
                    <xsl:variable name="newtext" select="translate($vrsta,'_',' ')"/>
                    <xsl:choose>
                        <xsl:when test="$newtext='ostalo'">
                            <xsl:value-of select="a1:Zahtev_za_autorska_dela/a1:Autorsko_delo/a1:Vrsta/@Ostala_vrsta_dela"/>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:value-of select="$newtext"/>
                        </xsl:otherwise>
                    </xsl:choose>
                </pred:Vrsta_autorskog_dela>

                <pred:Format>
                    <xsl:variable name="format" select="a1:Zahtev_za_autorska_dela/a1:Autorsko_delo/a1:Format_zapisa"/>
                    <xsl:variable name="newFormat" select="translate($format,'_',' ')"/>
                    <xsl:choose>
                        <xsl:when test="$newFormat='ostalo'">
                            <xsl:value-of select="a1:Zahtev_za_autorska_dela/a1:Autorsko_delo/a1:Format_zapisa/@Ostali_format"/>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:value-of select="$newFormat"/>
                        </xsl:otherwise>
                    </xsl:choose>

                </pred:Format>

                <xsl:variable name="ime" select="a1:Zahtev_za_autorska_dela/a1:Autor/a1:Ime"/>
                <xsl:choose>
                    <xsl:when test="$ime!='undefined'">
                        <pred:Autor_ime>
                            <xsl:value-of select="a1:Zahtev_za_autorska_dela/a1:Autor/a1:Ime"/>
                        </pred:Autor_ime>
                        <pred:Autor_prezime>
                            <xsl:value-of select="a1:Zahtev_za_autorska_dela/a1:Autor/a1:Prezime"/>
                        </pred:Autor_prezime>
                    </xsl:when>
                </xsl:choose>



            </rdf:Description>

        </rdf:RDF>
    </xsl:template>
</xsl:stylesheet>