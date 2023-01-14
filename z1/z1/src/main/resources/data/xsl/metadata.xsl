<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
                xmlns:z1="http://ftn.uns.ac.rs/z"
                xmlns:pred="http://ftn.uns.ac.rs/z/pred/"
                xmlns:sema="http://ftn.uns.ac.rs/sema"
>
    <xsl:template match="/">
        <rdf:RDF>
            <xsl:variable name="Zahtev_za_zig">Z-<xsl:value-of select="//z1:Zahtev_za_zig/attribute::Broj_prijave"/></xsl:variable>
            <rdf:Description rdf:about="{$Zahtev_za_zig}">
                <pred:Broj>
                    <xsl:value-of select="//z1:Zahtev_za_zig/attribute::Broj_prijave"/>
                </pred:Broj>

                <pred:Datum_podnosenja>
                    <xsl:value-of select="//z1:Zahtev_za_zig/attribute::Datum_podnosenja"/>
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

            </rdf:Description>
        </rdf:RDF>
    </xsl:template>
</xsl:stylesheet>