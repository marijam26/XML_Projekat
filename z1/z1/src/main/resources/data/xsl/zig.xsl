<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0"
                xmlns:sema="http://ftn.uns.ac.rs/sema"
                xmlns:z="http://ftn.uns.ac.rs/z"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>
    <xsl:template match="/">
        <html>
            <head>
                <title>Zig</title>
                <style>
                    * {
                        margin: 0;
                        padding: 0;
                        text-indent: 0;
                    }
                    body {
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

                    h3 {
                        font-weight: bold;
                        font-size: 9pt;
                    }

                    table{
                        border-collapse:collapse;
                        border:1px solid black;
                        table-layout: fixed;
                        margin-left: 15.15pt;
                        width: 100%;
                        height: auto;
                        border-spacing: 0;
                    }

                    table td{
                        border:1px solid black;
                        border-collapse:collapse;
                    }

                </style>
            </head>

            <body>
                <h1 style="padding-top: 40pt; text-align: center;">
                    ЗАХТЕВ ЗА ПРИЗНАЊЕ ЖИГА
                </h1>
                <h3 style="text-align: center;">
                    Заводy за интелектуалну својину, Кнегиње Љубице 5, 11000 Београд
                </h3>

                <br/>

                <p style="text-align: center; font-size: 9pt; margin-bottom: 3px;">
                    (попунити на рачунару)
                </p>

                <table style="">
                    <tr style="height: 25pt">
                        <td style="width: 100%;" colspan="3">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                <b>1. Подносилац пријаве:</b> име и презиме-пословно име, улица и број, поштански број, место,
                                <br/>
                                <span style="padding-left: 10pt">држава пребивалишта/седишта:</span>
                            </p>
                        </td>
                    </tr>
                    <tr style="height: 50pt">
                        <td style="width: 100%;" colspan="3">
                            <p style="font-size: 8.5pt;  padding-top: 6pt; padding-left: 7pt; line-height: 10pt;">
                                <xsl:variable name="tlice" select="z:Zahtev_za_zig/sema:Podnosilac_prijave/@xsi:type"/>
                                <xsl:choose>
                                    <xsl:when test="substring($tlice,5)='TFizicko_lice'">
                                        <p style="font-size: 9pt; padding-top: 6pt; padding-left: 7pt;">
                                            <xsl:value-of select="z:Zahtev_za_zig/sema:Podnosilac_prijave/sema:Ime"/>&#160;
                                            <xsl:value-of select="z:Zahtev_za_zig/sema:Podnosilac_prijave/sema:Prezime"/>
                                        </p>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <p style="font-size: 9pt; padding-top: 6pt; padding-left: 7pt;">
                                            <xsl:value-of select="z:Zahtev_za_zig/sema:Podnosilac_prijave/sema:Poslovno_ime"/>
                                        </p>
                                    </xsl:otherwise>
                                </xsl:choose>

                                <p style="font-size: 9pt; padding-left: 7pt;">
                                    <xsl:value-of select="z:Zahtev_za_zig/sema:Podnosilac_prijave/sema:Adresa/sema:Ulica"/>&#160;
                                    <xsl:value-of select="z:Zahtev_za_zig/sema:Podnosilac_prijave/sema:Adresa/sema:Broj"/>,&#160;
                                    <xsl:value-of select="z:Zahtev_za_zig/sema:Podnosilac_prijave/sema:Adresa/sema:Postanski_broj"/>
                                </p>
                                <p style="font-size: 9pt; padding-left: 7pt;">
                                    <xsl:value-of select="z:Zahtev_za_zig/sema:Podnosilac_prijave/sema:Adresa/sema:Grad"/>&#160;
                                    <xsl:value-of select="z:Zahtev_za_zig/sema:Podnosilac_prijave/sema:Adresa/sema:Drzava"/>
                                </p>
                            </p>
                        </td>
                    </tr>
                    <tr style="height: 15pt">
                        <td  colspan="1">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                телефон: <xsl:value-of select="z:Zahtev_za_zig/sema:Podnosilac_prijave/sema:Kontakt/sema:Telefon"/>
                            </p>
                        </td>
                        <td  colspan="1">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                e-mail: <xsl:value-of select="z:Zahtev_za_zig/sema:Podnosilac_prijave/sema:Kontakt/sema:E_posta"/>
                            </p>
                        </td>
                        <td colspan="1">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                факс: <xsl:value-of select="z:Zahtev_za_zig/sema:Podnosilac_prijave/sema:Kontakt/sema:Faks"/>
                            </p>
                        </td>
                    </tr>

                    <tr style="height: 25pt">
                        <td style="width: 100%;" colspan="3">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                <b>2. Пуномоћник:</b> име и презиме-пословно име, улица и број, поштански број, држава
                                <br/>
                                <span style="padding-left: 10pt">пребивалишта/седишта:</span>
                            </p>
                        </td>
                    </tr>
                    <tr style="height: 50pt">
                        <td style="width: 100%;" colspan="3">
                            <p style="font-size: 9pt;  padding-top: 6pt; padding-left: 7pt;">
                                <xsl:variable name="tlice" select="z:Zahtev_za_zig/sema:Punomocnik/@xsi:type"/>
                                <xsl:choose>
                                    <xsl:when test="substring($tlice,5)='TFizicko_lice'">
                                        <p style="font-size: 9pt; padding-top: 6pt; padding-left: 7pt;">
                                            <xsl:value-of select="z:Zahtev_za_zig/sema:Punomocnik/sema:Ime"/>&#160;
                                            <xsl:value-of select="z:Zahtev_za_zig/sema:Punomocnik/sema:Prezime"/>
                                        </p>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <p style="font-size: 9pt; padding-top: 6pt; padding-left: 7pt;">
                                            <xsl:value-of select="z:Zahtev_za_zig/sema:Punomocnik/sema:Poslovno_ime"/>
                                        </p>
                                    </xsl:otherwise>
                                </xsl:choose>

                                <p style="font-size: 9pt; padding-left: 7pt;">
                                    <xsl:value-of select="z:Zahtev_za_zig/sema:Punomocnik/sema:Adresa/sema:Ulica"/>&#160;
                                    <xsl:value-of select="z:Zahtev_za_zig/sema:Punomocnik/sema:Adresa/sema:Broj"/>,&#160;
                                    <xsl:value-of select="z:Zahtev_za_zig/sema:Punomocnik/sema:Adresa/sema:Postanski_broj"/>
                                </p>
                                <p style="font-size: 9pt; padding-left: 7pt;">
                                    <xsl:value-of select="z:Zahtev_za_zig/sema:Punomocnik/sema:Adresa/sema:Grad"/>&#160;
                                    <xsl:value-of select="z:Zahtev_za_zig/sema:Punomocnik/sema:Adresa/sema:Drzava"/>
                                </p>
                            </p>
                        </td>
                    </tr>
                    <tr style="height: 15pt">
                        <td  colspan="1">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                телефон: <xsl:value-of select="z:Zahtev_za_zig/sema:Punomocnik/sema:Kontakt/sema:Telefon"/>
                            </p>
                        </td>
                        <td  colspan="1">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                e-mail: <xsl:value-of select="z:Zahtev_za_zig/sema:Punomocnik/sema:Kontakt/sema:E_posta"/>
                            </p>
                        </td>
                        <td colspan="1">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                факс: <xsl:value-of select="z:Zahtev_za_zig/sema:Punomocnik/sema:Kontakt/sema:Faks"/>
                            </p>
                        </td>
                    </tr>

                    <tr style="height: 15pt">
                        <td style="width: 100%;" colspan="3">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                <b>3. Подаци о заједничком представнику ако постоји више подносилаца пријаве</b>
                            </p>
                        </td>
                    </tr>
                    <tr style="height: 50pt">
                        <td style="width: 100%;" colspan="3">
                        </td>
                    </tr>

                    <tr style="height: 15pt">
                        <td  colspan="1">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                телефон:
                            </p>
                        </td>
                        <td  colspan="1">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                e-mail:
                            </p>
                        </td>
                        <td colspan="1">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                факс:
                            </p>
                        </td>
                    </tr>

                </table>

                <table style="border-top: 0;">
                    <tr style="height: 15pt; border-top: 0;">
                        <td style="width: 100%;" colspan="8">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                <b>4. Пријава се подноси за (уписати X):</b>
                            </p>
                        </td>
                        <td style="width: 100%; position: relative" rowspan="2" colspan="8">
                            <p style="padding-left: 5pt; text-align: left; vertical-align: bottom; font-size: 9pt">
                                <b>в) изглед знака:</b>
                            </p>
                        </td>
                    </tr>

                    <tr style="height: 12pt">
                        <td style="width: 100%; position: relative" colspan="1" rowspan="3">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt; vertical-align: center">
                                <b>а)</b>
                            </p>
                        </td>
                        <td style="width: 100%;" colspan="5">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                индивидуални жиг
                            </p>
                        </td>
                        <td style="width: 100%; display: table-cell; vertical-align: center" colspan="2">
                            <xsl:variable name="tip_ziga" select="z:Zahtev_za_zig/z:Zig/@Tip_ziga"/>
                            <xsl:choose>
                                <xsl:when test="$tip_ziga='individualni'">
                                    <p style="font-size: 9pt; text-align: center;">
                                       X
                                    </p>
                                </xsl:when>
                            </xsl:choose>
                        </td>
                    </tr>

                    <tr style="height: 12pt">
                        <td style="width: 100%;" colspan="5">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                колективни жиг
                            </p>
                        </td>
                        <td style="width: 100%;" colspan="2">
                            <xsl:variable name="tip_ziga" select="z:Zahtev_za_zig/z:Zig/@Tip_ziga"/>
                            <xsl:choose>
                                <xsl:when test="$tip_ziga='kolektivni'">
                                    <p style="font-size: 9pt; text-align: center;">
                                        X
                                    </p>
                                </xsl:when>
                            </xsl:choose>
                        </td>
                        <td style="width: 100%;" colspan="8" rowspan="16">
                        </td>
                    </tr>

                    <tr style="height: 12pt">
                        <td style="width: 100%;" colspan="5">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                жиг гаранције
                            </p>
                        </td>
                        <td style="width: 100%;" colspan="2">
                            <xsl:variable name="tip_ziga" select="z:Zahtev_za_zig/z:Zig/@Tip_ziga"/>
                            <xsl:choose>
                                <xsl:when test="$tip_ziga='zig_garancije'">
                                    <p style="font-size: 9pt; text-align: center;">
                                        X
                                    </p>
                                </xsl:when>
                            </xsl:choose>
                        </td>
                    </tr>

                    <tr style="height: 12pt">
                        <td style="width: 100%; position: relative" colspan="1" rowspan="5">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt; vertical-align: center">
                                <b>б)</b>
                            </p>
                        </td>
                        <td style="width: 100%;" colspan="5">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                вербални знак (знак у речи)
                            </p>
                        </td>
                        <td style="width: 100%;" colspan="2">
                            <xsl:variable name="vrsta_znaka" select="z:Zahtev_za_zig/z:Zig/@Vrsta_znaka"/>
                            <xsl:choose>
                                <xsl:when test="$vrsta_znaka='verbalni'">
                                    <p style="font-size: 9pt; text-align: center;">
                                        X
                                    </p>
                                </xsl:when>
                            </xsl:choose>
                        </td>
                    </tr>

                    <tr style="height: 12pt">
                        <td style="width: 100%;" colspan="5">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                графички знак, боју, комбинацију <br/>
                                boja
                            </p>
                        </td>
                        <td style="width: 100%;" colspan="2">
                            <xsl:variable name="vrsta_znaka" select="z:Zahtev_za_zig/z:Zig/@Vrsta_znaka"/>
                            <xsl:choose>
                                <xsl:when test="$vrsta_znaka='graficki'">
                                    <p style="font-size: 9pt; text-align: center;">
                                        X
                                    </p>
                                </xsl:when>
                            </xsl:choose>
                        </td>
                    </tr>


                    <tr style="height: 12pt">
                        <td style="width: 100%;" colspan="5">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                комбиновани знак
                            </p>
                        </td>
                        <td style="width: 100%;" colspan="2">
                            <xsl:variable name="vrsta_znaka" select="z:Zahtev_za_zig/z:Zig/@Vrsta_znaka"/>
                            <xsl:choose>
                                <xsl:when test="$vrsta_znaka='kombinovani'">
                                    <p style="font-size: 9pt; text-align: center;">
                                        X
                                    </p>
                                </xsl:when>
                            </xsl:choose>
                        </td>
                    </tr>


                    <tr style="height: 12pt">
                        <td style="width: 100%;" colspan="5">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                тродимензионални знак
                            </p>
                        </td>
                        <td style="width: 100%;" colspan="2">
                            <xsl:variable name="vrsta_znaka" select="z:Zahtev_za_zig/z:Zig/@Vrsta_znaka"/>
                            <xsl:choose>
                                <xsl:when test="$vrsta_znaka='trodimenzionalni'">
                                    <p style="font-size: 9pt; text-align: center;">
                                        X
                                    </p>
                                </xsl:when>
                            </xsl:choose>
                        </td>
                    </tr>


                    <tr style="height: 12pt">
                        <td style="width: 100%;" colspan="5">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                другу врсту знака (навести коју)
                            </p>
                        </td>
                        <td style="width: 100%;" colspan="2">
                            <xsl:variable name="vrsta_znaka" select="z:Zahtev_za_zig/z:Zig/@Vrsta_znaka"/>
                            <xsl:choose>
                                <xsl:when test="$vrsta_znaka='ostalo'">
                                    <p style="font-size: 9pt; text-align: center;">
                                        <xsl:value-of select="z:Zahtev_za_zig/z:Zig/@Ostalo"/>
                                    </p>
                                </xsl:when>
                            </xsl:choose>
                        </td>
                    </tr>

                    <tr style="height: 40pt">
                        <td style="width: 100%; position: relative; display: table-cell; vertical-align: top" colspan="8" rowspan="3">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt; vertical-align: top">
                                <b>5. Назначење боје, односно боја из којих се знак <br/>
                                    састоји:</b>
                            </p>
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt; vertical-align: bottom;">
                                <xsl:value-of select="z:Zahtev_za_zig/z:Zig/z:Boja"/>
                            </p>
                        </td>
                    </tr>

                    <tr/>
                    <tr/>

                    <tr style="height: 30pt">
                        <td style="width: 100%; display: table-cell; vertical-align: top" colspan="8" rowspan="2">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                <b>6. Транслитерација знака*:</b>
                            </p>
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt; vertical-align: bottom;">
                                <xsl:value-of select="z:Zahtev_za_zig/z:Zig/z:Transliteracija"/>
                            </p>
                        </td>
                    </tr>

                    <tr/>

                    <tr style="height: 30pt">
                        <td style="width: 100%; display: table-cell; vertical-align: top" colspan="8" rowspan="2">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                <b>7. Превод знака*:</b>
                            </p>
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt; vertical-align: bottom;">
                                <xsl:value-of select="z:Zahtev_za_zig/z:Zig/z:Prevod"/>
                            </p>
                        </td>
                    </tr>

                    <tr/>

                    <tr style="height: 30pt">
                        <td style="width: 100%; position: relative; display: table-cell; vertical-align: top" colspan="8" rowspan="2">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt;">
                                <b>8. Опис знака*:</b>
                            </p>
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt; vertical-align: bottom;">
                                <xsl:value-of select="z:Zahtev_za_zig/z:Zig/z:Opis"/>
                            </p>
                        </td>
                    </tr>

                    <tr/>

                </table>

                <table>
                    <tr style="height: 15pt">
                        <td style="width: 100%; position: relative; display: table-cell; vertical-align: top" colspan="23">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt;">
                                <b>9. Заокружити бројеве класа робе и услуга према Ничанској класификацији:</b>
                            </p>
                        </td>
                    </tr>


                    <tr>
                        <xsl:variable name="klase" select="z:Zahtev_za_zig/z:Zig/z:Klasa_robe"/>

                        <xsl:for-each select="1 to 23">
                            <xsl:choose>
                                <xsl:when test="position() = $klase">
                                    <td style="background-color: black; padding-left: 5pt; text-align: left; font-size: 9pt;">
                                        <xsl:value-of select="position()"/>
                                    </td>
                                </xsl:when>
                                <xsl:otherwise>
                                    <td style="padding-left: 5pt; text-align: left; font-size: 9pt;">
                                        <xsl:value-of select="position()"/>
                                    </td>
                                </xsl:otherwise>
                            </xsl:choose>
                        </xsl:for-each>
                    </tr>

                    <tr>
                        <xsl:variable name="klase" select="z:Zahtev_za_zig/z:Klasa_robe"/>
                        <xsl:for-each select="24 to 45">
                            <xsl:choose>
                                <xsl:when test="position()+23 = $klase">
                                    <td style="background-color: black; padding-left: 5pt; text-align: left; font-size: 9pt;">
                                        <xsl:value-of select="position()+23"/>
                                    </td>
                                </xsl:when>
                                <xsl:otherwise>
                                    <td style="padding-left: 5pt; text-align: left; font-size: 9pt;"><xsl:value-of select="position()+23"/></td>
                                </xsl:otherwise>
                            </xsl:choose>
                        </xsl:for-each>

                        <td/>
                    </tr>

                    <tr style="height: 40px">
                        <td style="width: 100%; position: relative; display: table-cell; vertical-align: top; margin-top:0;" colspan="23" rowspan="2">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt;">
                                <b>10. Затражено право првенства и основ:</b>
                            </p>
                        </td>
                    </tr>

                    <tr/>
                </table>

                <p style="text-align: center; font-size: 9pt; padding-top: 10px; padding-bottom: 50px;">
                    *Попунити само ако је знак или елемент знака исписан словима која нису ћирилична или латинична
                </p>

                <table style="padding-bottom: 100px; margin-top: 50px; ">
                    <tr style="height: 25pt">
                        <td style="width: 100%; display: table-cell; vertical-align: top" colspan="3">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                <b>11. Плаћене таксе:</b>
                            </p>
                        </td>

                        <td style="width: 100%; display: table-cell; vertical-align: top" colspan="1">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                <b>Динара</b>
                            </p>
                        </td>

                        <td style="width: 100%; display: table-cell; vertical-align: top" colspan="3" rowspan="4">
                            <p style="padding-left: 5pt; text-align: center; font-size: 9pt">
                                <b>Потпис подносиоца захтева</b>
                            </p>
                        </td>
                    </tr>

                    <tr style="height: 25pt">
                        <td style="width: 100%; display: table-cell; vertical-align: center" colspan="3">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                <b>а) основна такса</b>
                            </p>
                        </td>

                        <td style="width: 100%;" colspan="1">
                            <p style="padding-left: 5pt; text-align: center; font-size: 9pt">
                                <xsl:value-of select="z:Zahtev_za_zig/z:Takse/z:Osnovna_taksa"/>
                            </p>
                        </td>
                    </tr>

                    <tr style="height: 50pt">
                        <td style="width: 100%; display: table-cell; vertical-align: center" colspan="3">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                <b>б) за <xsl:value-of select="z:Zahtev_za_zig/z:Takse/z:Broj_klasa"/> класа</b> <br/>
                                <b>б) за  графичко решење</b>
                            </p>
                        </td>

                        <td style="width: 100%;" colspan="1">
                            <p style="padding-left: 5pt; text-align: center; font-size: 9pt">
                                <xsl:value-of select="z:Zahtev_za_zig/z:Takse/z:Klase"/><br/>
                                <xsl:value-of select="z:Zahtev_za_zig/z:Takse/z:Graficko_resenje"/>
                            </p>
                        </td>

                    </tr>

                    <tr style="height: 25pt">
                        <td style="width: 100%; display: table-cell; vertical-align: center" colspan="3">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                <b>УКУПНО</b>
                            </p>
                        </td>

                        <td style="width: 100%;" colspan="1">
                            <p style="padding-left: 5pt; text-align: center; font-size: 9pt">
                                <xsl:value-of select="z:Zahtev_za_zig/z:Takse/z:Ukupno"/>
                            </p>
                        </td>
                    </tr>
                </table>

                <table style="padding-top: 100px; margin-top: 50px; margin-bottom: 100px">
                    <tr style="height: 20pt">
                        <td style="width: 100%; display: table-cell; vertical-align: bottom" colspan="11">
                            <p style="padding-left: 5pt; text-align: center; font-size: 10pt">
                                <b>ПОПУЊАВА ЗАВОД</b>
                            </p>
                        </td>
                    </tr>

                    <tr style="height: 20pt">
                        <td style="width: 100%; display: table-cell; vertical-align: top" colspan="6">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                <b>Прилози уз захтев: </b>
                            </p>
                        </td>

                        <td style="width: 100%; display: table-cell; vertical-align: center" colspan="5" rowspan="9">
                            <p style="padding-left: 5pt; text-align: center; font-size: 9pt">
                                Број пријаве жига: <br/><br/>
                                Ж-<xsl:value-of select="z:Zahtev_za_zig/@Broj_prijave"/>/<xsl:value-of select="substring(z:Zahtev_za_zig/@Datum_podnosenja, 1, 4)"/><br/><br/>
                                <b>Датум подношења: </b> <br/><br/>
                                <xsl:value-of select="z:Zahtev_za_zig/@Datum_podnosenja"/>
                            </p>
                        </td>
                    </tr>

                    <tr style="height: 20pt">
                        <td style="width: 100%; display: table-cell; vertical-align: top" colspan="5">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                Примерак знака
                            </p>
                        </td>

                        <td style="width: 100%; display: table-cell; vertical-align: center" colspan="1">
                            <xsl:variable name="primerak" select="z:Zahtev_za_zig/z:Prilozi/z:Primerak_znaka"/>
                            <xsl:choose>
                                <xsl:when test="$primerak = 'true'">
                                    <p style="font-size: 9pt; text-align: center;">
                                        X
                                    </p>
                                </xsl:when>
                            </xsl:choose>
                        </td>
                    </tr>

                    <tr style="height: 20pt">
                        <td style="width: 100%; display: table-cell; vertical-align: top" colspan="5">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                Списак робе и услуга**
                            </p>
                        </td>

                        <td style="width: 100%; display: table-cell; vertical-align: center" colspan="1">
                            <xsl:variable name="spisak_robe" select="z:Zahtev_za_zig/z:Prilozi/z:Spisak_robe"/>
                            <xsl:choose>
                                <xsl:when test="$spisak_robe = 'true'">
                                    <p style="font-size: 9pt; text-align: center;">
                                        X
                                    </p>
                                </xsl:when>
                            </xsl:choose>
                        </td>
                    </tr>

                    <tr style="height: 20pt">
                        <td style="width: 100%; display: table-cell; vertical-align: top" colspan="5">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                Пуномоћје
                            </p>
                        </td>

                        <td style="width: 100%; display: table-cell; vertical-align: center" colspan="1">
                            <xsl:variable name="punomoc" select="z:Zahtev_za_zig/z:Prilozi/z:Punomoc"/>
                            <xsl:choose>
                                <xsl:when test="$punomoc = 'true'">
                                    <p style="font-size: 9pt; text-align: center;">
                                        X
                                    </p>
                                </xsl:when>
                            </xsl:choose>
                        </td>
                    </tr>


                    <tr style="height: 20pt">
                        <td style="width: 100%; display: table-cell; vertical-align: top" colspan="5">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                Генерално пуномоћје раније приложено
                            </p>
                        </td>

                        <td style="width: 100%; display: table-cell; vertical-align: center" colspan="1">
                            <xsl:variable name="ranija_punomoc" select="z:Zahtev_za_zig/z:Prilozi/z:Ranija_punomoc"/>
                            <xsl:choose>
                                <xsl:when test="$ranija_punomoc = 'true'">
                                    <p style="font-size: 9pt; text-align: center;">
                                        X
                                    </p>
                                </xsl:when>
                            </xsl:choose>
                        </td>
                    </tr>

                    <tr style="height: 20pt">
                        <td style="width: 100%; display: table-cell; vertical-align: top" colspan="5">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                Пуномоћје ће бити накнадно достављено
                            </p>
                        </td>

                        <td style="width: 100%; display: table-cell; vertical-align: center" colspan="1">
                            <xsl:variable name="naknadna_punomoc" select="z:Zahtev_za_zig/z:Prilozi/z:Naknadna_punomoc"/>
                            <xsl:choose>
                                <xsl:when test="$naknadna_punomoc = 'true'">
                                    <p style="font-size: 9pt; text-align: center;">
                                        X
                                    </p>
                                </xsl:when>
                            </xsl:choose>
                        </td>
                    </tr>

                    <tr style="height: 20pt">
                        <td style="width: 100%; display: table-cell; vertical-align: top" colspan="5">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                Општи акт о колективном жигу/жигу гаранције
                            </p>
                        </td>

                        <td style="width: 100%; display: table-cell; vertical-align: center" colspan="1">
                            <xsl:variable name="opsti_akt" select="z:Zahtev_za_zig/z:Prilozi/z:Opsti_akt"/>
                            <xsl:choose>
                                <xsl:when test="$opsti_akt = 'true'">
                                    <p style="font-size: 9pt; text-align: center;">
                                        X
                                    </p>
                                </xsl:when>
                            </xsl:choose>
                        </td>
                    </tr>

                    <tr style="height: 20pt">
                        <td style="width: 100%; display: table-cell; vertical-align: top" colspan="5">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                Доказ о праву првенства
                            </p>
                        </td>

                        <td style="width: 100%; display: table-cell; vertical-align: center" colspan="1">
                            <xsl:variable name="pravo" select="z:Zahtev_za_zig/z:Prilozi/z:Dokaz_o_pravu_prvenstva"/>
                            <xsl:choose>
                                <xsl:when test="$pravo = 'true'">
                                    <p style="font-size: 9pt; text-align: center;">
                                        X
                                    </p>
                                </xsl:when>
                            </xsl:choose>
                        </td>
                    </tr>

                    <tr style="height: 20pt">
                        <td style="width: 100%; display: table-cell; vertical-align: top" colspan="5">
                            <p style="padding-left: 5pt; text-align: left; font-size: 9pt">
                                Доказ о уплати таксе
                            </p>
                        </td>

                        <td style="width: 100%; display: table-cell; vertical-align: center" colspan="1">
                            <xsl:variable name="taksa" select="z:Zahtev_za_zig/z:Prilozi/z:Dokaz_o_uplati"/>
                            <xsl:choose>
                                <xsl:when test="$taksa = 'true'">
                                    <p style="font-size: 9pt; text-align: center;">
                                        X
                                    </p>
                                </xsl:when>
                            </xsl:choose>
                        </td>
                    </tr>

                </table>

                <div style="display: block; justify-content: center; text-align: justify">
                    <p style="font-size: 9pt; display: block; margin-left: 15.15pt;">
                        **Уз заокруживање броја класе робе/услуга Ничанске класификације у рубрици 9 доставља се и списак који
                        садржи конкретне називе робе коју подносилац пријаве производи, односно услуга које пружа. У циљу
                        одређења обима заштите која се стиче жигом, списак треба да садржи јасне и прецизне називе робе и
                        услуга. У ту сврху могу се користити појмови из детаљне Листе роба и услуга или MGS Manager апликације,
                        доступних на сајту Завода. Уколико се у списак уносе термини из Листе класа Ничанске класификације,
                        заштита обухвата само тако именоване, конкретне робе/услуге у њиховом јасном и недвосмисленом
                        значењу.
                    </p>
                </div>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>