<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:r="http://ftn.uns.ac.rs/r"  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>Autorsko delo (XSLT)</title>
                <style type="text/css">
                    * {
                    margin: 0;
                    padding: 0;
                    text-indent: 0;
                    }
                    body{
                    font-family: Arial, sans-serif;
                    font-style: normal;
                    text-decoration: none;
                    }

                    p {
                    font-weight: normal;
                    font-size: 10.5pt;
                    margin: 0pt;
                    }
                    h1 {
                    font-weight: bold;
                    font-size: 12pt;
                    }
                    .s5 {
                    font-weight: bold;
                    font-size: 8.5pt;
                    }
                    .s7 {
                    font-family: "MS Gothic", monospace;
                    font-weight: normal;
                    font-size: 10.5pt;
                    }

                    li {
                    display: block;
                    }

                    .zvezdica > li > *:first-child:before {
                    content: "* ";
                    font-weight: normal;
                    font-size: 8pt;
                    }

                    .kockica > li > *:first-child:before{
                    content: "☐ ";
                    font-weight: normal;
                    font-size: 10.5pt;
                    }

                    .all_borders {
                    border-top-style: solid;
                    border-top-width: 1pt;
                    border-left-style: solid;
                    border-left-width: 1pt;
                    border-bottom-style: solid;
                    border-bottom-width: 1pt;
                    border-right-style: solid;
                    border-right-width: 1pt;
                    }

                    .top_bottom{
                    border-top-style: solid;
                    border-top-width: 1pt;
                    border-bottom-style: solid;
                    border-bottom-width: 1pt;
                    }

                    .left_right{
                    border-left-style: solid;
                    border-left-width: 1pt;
                    border-right-style: solid;
                    border-right-width: 1pt;
                    }

                    .top_border{
                    border-top-style: solid;
                    border-top-width: 1pt;
                    }

                    table,
                    tbody {
                    vertical-align: top;
                    overflow: visible;
                    }
                </style>
            </head>
            <body>

                <table style="border-collapse: collapse; margin-left: 19pt;width: 95%;margin-bottom:160px;margin-top: 80pt;"
                       cellspacing="0" class="center">

                    <tr style="height: 80pt">
                        <td  style="width: 100%;" colspan="2">
                            <p style="font-size: 8pt;padding-top: 30pt;padding-bottom: 30pt; padding-left: 5pt;text-align: center;">
                                <h1 style="text-align: center;">
                                    Resenje zahteva za unosenje i evidenciju deponovanja autorskog dela
                                </h1>
                            </p>
                        </td>
                    </tr>
                    <tr style="height: 160pt">
                        <td  style="width: 100%;" colspan="2">
                            <p style="padding-left: 5pt;text-align: left;font-size:11pt;">

                            </p>
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                                Broj prijave: <xsl:value-of select="r:Resenje/r:referenca"/>      A-10-2023

                            </p>
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                                Datum razresenja: <xsl:value-of select="r:Resenje/r:datumRazresenjaZahteva"/> 12.02.2023.

                            </p>
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                                Ime sluzbenika: <xsl:value-of select="r:Resenje/r:imeSluzbenika"/>              Pera

                            </p>
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                                Prezime sluzbenika: <xsl:value-of select="r:Resenje/r:prezimeSluzbenika"/>      Peric

                            </p>
                            Status:
                            <xsl:variable name="vrsta" select="r:Resenje/r:Odobren"/>
                            <xsl:variable name="newtext" select="translate($vrsta,'_',' ')"/>
                            <xsl:choose>
                                <xsl:when test="$newtext='true'">
                                    Prihvacen
                                </xsl:when>
                                <xsl:otherwise>
                                    Odbijen
                                </xsl:otherwise>
                            </xsl:choose>
                        </td>
                    </tr>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
