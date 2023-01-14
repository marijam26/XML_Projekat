<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
                xmlns:p1="http://ftn.uns.ac.rs/p"
                xmlns:pred="http://ftn.uns.ac.rs/p/pred/"
                xmlns:sema="http://ftn.uns.ac.rs/sema"
>
    <xsl:template match="/">
        <rdf:RDF>
            <xsl:variable name="Zahtev_za_patent">P-<xsl:value-of select="//p1:Zahtev_za_patent/@Broj_prijave"/></xsl:variable>
            <rdf:Description rdf:about="{$Zahtev_za_patent}">
                <pred:Broj>
                    <xsl:value-of select="//p1:Zahtev_za_patent/@Broj_prijave"/>
                </pred:Broj>

                <pred:Datum_podnosenja>
                    <xsl:value-of select="//p1:Zahtev_za_patent/@Datum_podnosenja"/>
                </pred:Datum_podnosenja>

                <pred:Datum_prijema>
                    <xsl:value-of select="//p1:Zahtev_za_patent/@Datum_prijema"/>
                </pred:Datum_prijema>

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

                <pred:Pronalazac_ime>
                    <xsl:value-of select="//p1:Pronalazac/sema:Ime"/>
                </pred:Pronalazac_ime>
                <pred:Pronalazac_prezime>
                    <xsl:value-of select="//p1:Pronalazac/sema:Prezime"/>
                </pred:Pronalazac_prezime>

                <pred:Pronalazak_naziv_na_engleskom>
                    <xsl:value-of select="//p1:Pronalazak/p1:Engleski_naziv"/>
                </pred:Pronalazak_naziv_na_engleskom>

                <pred:Pronalazak_naziv_na_srpskom>
                    <xsl:value-of select="//p1:Pronalazak/p1:Srpski_naziv"/>
                </pred:Pronalazak_naziv_na_srpskom>

                <xsl:for-each select="//p1:Ranija_prijava">
                    <pred:Broj_ranije_prijave>
                        <xsl:value-of select="p1:Broj"/>
                    </pred:Broj_ranije_prijave>
                </xsl:for-each>


            </rdf:Description>

        </rdf:RDF>
    </xsl:template>
</xsl:stylesheet>