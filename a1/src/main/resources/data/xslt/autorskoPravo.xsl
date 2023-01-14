<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:a="http://ftn.uns.ac.rs/a" xmlns:sema="http://ftn.uns.ac.rs/sema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.0">

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

                    <tr style="height: 20pt;padding-top: 10pt;">
                         <td style="border-top-style: solid;border-top-width: 1pt;border-left-style: solid;border-left-width: 1pt;">
                             <p class="s5" style="padding-left: 5pt;padding-top: 3pt;text-align: left;" >
                                 ЗАВОД ЗА ИНТЕЛЕКТУАЛНУ СВОЈИНУ
                             </p>
                         </td>
                        <td style="border-top-style: solid;border-top-width: 1pt;border-right-style: solid;border-right-width: 1pt;">
                             <p class="s5" style="padding-left: 5pt;padding-top: 3pt;text-align: left;" >
                                 ОБРАЗАЦ А-1
                             </p>
                         </td>
                    </tr>
                    <tr style="height: 80pt">
                        <td class="left_right" style="width: 100%;border-bottom-style: solid; border-bottom-width: 1pt;" colspan="2">
                            <p style="font-size: 8pt; padding-left: 5pt;text-align: left;">
                                Београд, Кнегиње Љубице 5
                            </p>
                            <p style="font-size: 8pt;padding-top: 30pt;padding-bottom: 30pt; padding-left: 5pt;text-align: center;">
                                <h1 style="text-align: center;">
                                    ЗАХТЕВ ЗА УНОШЕЊЕ У ЕВИДЕНЦИЈУ И ДЕПОНОВАЊЕ АУТОРСКИХ ДЕЛА
                                </h1>
                            </p>
                        </td>
                    </tr>
                    <tr style="height: 160pt">
                        <td class="left_right" style="width: 100%;" colspan="2">
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                                1) Подносилац - име, презиме, адреса и држављанство аутора или другог носиоца ауторског
                                права ако је подносилац физичко лице, односно пословно име и седиште носиоца ауторског
                                права ако је подносилац правно лице*:
                            </p>
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                                <xsl:variable name="tlice" select="a:Zahtev_za_autorska_dela/sema:Podnosilac_prijave/@xsi:type"/>
                                <xsl:choose>
                                    <xsl:when test="substring($tlice,5)='TFizicko_lice'">
                                        <p style="font-size: 11pt;padding-top: 6pt;padding-bottom: 6pt;padding-left: 7pt;line-height: 10pt;">
                                            Име и презиме: &#160;
                                            <xsl:value-of select="a:Zahtev_za_autorska_dela/sema:Podnosilac_prijave/sema:Ime"/>&#160; <xsl:value-of select="a:Zahtev_za_autorska_dela/sema:Podnosilac_prijave/sema:Prezime"/>, &#160;
                                            Држављанство: <xsl:value-of select="a:Zahtev_za_autorska_dela/sema:Podnosilac_prijave/sema:Drzavljanstvo"/>
                                        </p>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        Пословно име: <xsl:value-of select="a:Zahtev_za_autorska_dela/sema:Podnosilac_prijave/sema:Poslovno_ime"/>
                                    </xsl:otherwise>
                                </xsl:choose>
                                <br/>
                                <p style="font-size: 11pt; padding-left: 5pt;text-align: left;" >
                                    Улица и број, поштански број, место и држава:
                                </p>
                                <p style="font-size: 11pt; padding-top: 6pt;padding-left: 5pt;">
                                    <xsl:value-of select="a:Zahtev_za_autorska_dela/sema:Podnosilac_prijave/sema:Adresa/sema:Ulica"/>,&#160;
                                    <xsl:value-of select="a:Zahtev_za_autorska_dela/sema:Podnosilac_prijave/sema:Adresa/sema:Broj"/>,&#160;
                                    <xsl:value-of select="a:Zahtev_za_autorska_dela/sema:Podnosilac_prijave/sema:Adresa/sema:Postanski_broj"/>&#160;
                                    <xsl:value-of select="a:Zahtev_za_autorska_dela/sema:Podnosilac_prijave/sema:Adresa/sema:Grad"/>&#160;
                                    <xsl:value-of select="a:Zahtev_za_autorska_dela/sema:Podnosilac_prijave/sema:Adresa/sema:Drzava"/>
                                </p>
                            </p>
                        </td>
                    </tr>
                    <tr style="height: 23pt">

                        <td class="all_borders" style="width: 100%;"  >
                            телефон:&#160; <xsl:value-of select="a:Zahtev_za_autorska_dela/sema:Podnosilac_prijave/sema:Kontakt/sema:Telefon"/>
                        </td>
                        <td class="all_borders" style="width: 100%;" >
                            e-mail:&#160; <xsl:value-of select="a:Zahtev_za_autorska_dela/sema:Podnosilac_prijave/sema:Kontakt/sema:E_posta"/>
                        </td>

                    </tr>
                    <tr style="height: 100pt">
                        <td class="left_right" style="width: 100%;" colspan="2">
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                                2) Псеудоним или знак аутора, (ако га има):
                            </p>
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                                <xsl:value-of select="a:Zahtev_za_autorska_dela/a:Autor/a:Pseudonim"/>
                            </p>
                        </td>
                    </tr>
                    <tr style="height: 100pt">
                        <td class="left_right" style="width: 100%;" colspan="2">
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                                3) Име, презиме и адреса пуномоћника, ако се пријава подноси преко пуномоћника:
                            </p>
                            <p style="padding-left: 5pt;padding-bottom: 5pt;line-height: 139%;text-align: left;font-size:11pt;">
                                Име и презиме:
                                        <xsl:value-of select="a:Zahtev_za_autorska_dela/sema:Punomocnik/sema:Ime"/>&#160;
                                        <xsl:value-of select="a:Zahtev_za_autorska_dela/sema:Punomocnik/sema:Prezime"/>
                                <br/>
                                Aдреса:
                                <xsl:value-of select="a:Zahtev_za_autorska_dela/sema:Punomocnik/sema:Adresa/sema:Ulica"/>,&#160;
                                <xsl:value-of select="a:Zahtev_za_autorska_dela/sema:Punomocnik/sema:Adresa/sema:Broj"/>&#160;
                                <xsl:value-of select="a:Zahtev_za_autorska_dela/sema:Punomocnik/sema:Adresa/sema:Postanski_broj"/>&#160;&#160;

                                <xsl:value-of select="a:Zahtev_za_autorska_dela/sema:Punomocnik/sema:Adresa/sema:Grad"/>&#160;
                                <xsl:value-of select="a:Zahtev_za_autorska_dela/sema:Punomocnik/sema:Adresa/sema:Drzava"/>
                            </p>
                        </td>
                    </tr>
                    <tr style="height: 100pt">
                        <td class="left_right" style="width: 100%;" colspan="2">
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                                4) Наслов ауторског дела, односно алтернативни наслов, ако га има, по коме ауторско дело може да се идентификује*:
                            </p>
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                                <xsl:value-of select="a:Zahtev_za_autorska_dela/a:Autorsko_delo/a:Naslov"/>
                            </p>
                        </td>
                    </tr>
                    <tr style="height: 100pt">
                        <td class="left_right" style="width: 100%;" colspan="2">
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                            5) Подаци о наслову ауторског дела на коме се заснива дело прераде, ако је у питању ауторско дело прераде, као и податак о аутору изворног дела:
                            </p>
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                                Наслов изворног дела:  <xsl:value-of select="a:Zahtev_za_autorska_dela/a:Autorsko_delo/a:Izvorno_delo/a:Naslov"/>
                                <br/>
                                <xsl:variable name="anoniman" select="a:Zahtev_za_autorska_dela/a:Autorsko_delo/a:Izvorno_delo/a:Anoniman_autor"/>
                                <xsl:variable name="naslov" select="a:Zahtev_za_autorska_dela/a:Autorsko_delo/a:Izvorno_delo/a:Naslov"/>
                                Autor:
                                <xsl:choose>
                                    <xsl:when test="$anoniman=false and $naslov">
                                        <p style="font-size: 8.5pt;padding-top: 6pt;padding-left: 7pt;line-height: 10pt;">
                                        <xsl:value-of select="a:Zahtev_za_autorska_dela/a:Autorsko_delo/a:Izvorno_delo/a:Autor/a:Ime"/>&#160;
                                        <xsl:value-of select="a:Zahtev_za_autorska_dela/a:Autorsko_delo/a:Izvorno_delo/a:Autor/a:Prezime"/>
                                        </p>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        -
                                    </xsl:otherwise>
                                </xsl:choose>

                            </p>
                        </td>
                    </tr>
                    <tr style="height: 45pt">
                        <td class="left_right" style="width: 100%; border-bottom-style: solid; border-bottom-width: 1pt;" colspan="2">
                            <p style="padding-left: 5pt;padding-bottom: 5pt;line-height: 139%;text-align: left;font-size:11pt;">
                                6) Подаци о врсти ауторског дела (књижевно дело, музичко дело, ликовно дело, рачунарски програм и др.) *:
                            </p>
                            <p style="padding-left: 5                                                                                                                                                                                                                                          pt;padding-bottom: 5pt;line-height: 139%;text-align: left;font-size:11pt;">
                                <xsl:variable name="vrsta" select="a:Zahtev_za_autorska_dela/a:Autorsko_delo/a:Vrsta"/>
                                <xsl:variable name="newtext" select="translate($vrsta,'_',' ')"/>
                                <xsl:choose>
                                    <xsl:when test="$newtext='ostalo'">
                                        <xsl:value-of select="a:Zahtev_za_autorska_dela/a:Autorsko_delo/a:Vrsta/@Ostala_vrsta_dela"/>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="$newtext"/>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </p>
                        </td>
                    </tr>
                </table>
                <table style="border-collapse: collapse; margin-left: 19pt;width: 95%;margin-bottom:160px;"
                       cellspacing="0" class="center">
                    <tr style="height: 130pt">
                        <td class="left_right" style="width: 100%;border-top-style: solid; border-top-width: 1pt;" colspan="2">
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                                7) Подаци о форми записа ауторског дела (штампани текст, оптички диск и слично) *:
                            </p>
                            <p style="padding-left: 5pt;padding-bottom: 5pt;line-height: 139%;text-align: left;font-size:11pt;">
                                <xsl:variable name="format" select="a:Zahtev_za_autorska_dela/a:Autorsko_delo/a:Format_zapisa"/>
                                <xsl:variable name="newFormat" select="translate($format,'_',' ')"/>
                                <xsl:choose>
                                <xsl:when test="$newFormat='ostalo'">
                                    <xsl:value-of select="a:Zahtev_za_autorska_dela/a:Autorsko_delo/a:Format_zapisa/@Ostali_format"/>
                                </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="$newFormat"/>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </p>
                        </td>
                    </tr>
                    <tr style="height: 170pt">
                        <td class="left_right" style="width: 100%;" colspan="2">
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                                8) Подаци о аутору ако подносилац пријаве из тачке 1. овог захтева није аутор и то: презиме, име, адреса и држављанство аутора (групе аутора или коаутора), а ако су у питању један или више аутора који нису живи, имена аутора и године смрти аутора а ако је у питању ауторско дело анонимног аутора навод да је ауторско дело дело анонимног аутора:
                            </p>
                            <p style="padding-left: 10pt;">
                                <xsl:variable name="podnosilacJeAutor" select="a:Zahtev_za_autorska_dela/@Podnosilac_je_autor"/>
                                <xsl:choose>
                                    <xsl:when test="$podnosilacJeAutor='false'">
                                        <p style="font-size: 8.5pt;padding-top: 6pt;padding-left: 7pt;line-height: 10pt;">
                                            <xsl:variable name="ime" select="a:Zahtev_za_autorska_dela/a:Autor/a:Ime"/>
                                            <xsl:choose>
                                                <xsl:when test="$ime='undefined'">
                                                    Autor je anoniman.
                                                </xsl:when>
                                                    <xsl:otherwise>

                                                        <p style="font-size: 11pt;padding-top: 6pt;padding-left: 7pt;line-height: 10pt;">
                                                            Име и презиме: &#160;    <xsl:value-of select="a:Zahtev_za_autorska_dela/a:Autor/a:Ime"/>
                                                            &#160;    <xsl:value-of select="a:Zahtev_za_autorska_dela/a:Autor/a:Prezime"/>
                                                            <xsl:variable name="tАutor" select="a:Zahtev_za_autorska_dela/a:Autor/@xsi:type"/>
                                                            <xsl:choose>
                                                                <xsl:when test="substring($tАutor,5)='TZiv_autor'">
                                                                    <p style="font-size: 11pt;padding-top: 6pt;padding-left: 7pt;line-height: 10pt;">
                                                                        Адреса и држављанство:
                                                                        <br/>
                                                                        <xsl:value-of select="a:Zahtev_za_autorska_dela/a:Autor/sema:Adresa/sema:Ulica"/>,&#160;
                                                                        <xsl:value-of select="a:Zahtev_za_autorska_dela/a:Autor/sema:Adresa/sema:Broj"/>&#160;
                                                                        <xsl:value-of select="a:Zahtev_za_autorska_dela/a:Autor/sema:Adresa/sema:Postanski_broj"/>&#160;&#160;

                                                                        <xsl:value-of select="a:Zahtev_za_autorska_dela/a:Autor/sema:Adresa/sema:Grad"/>&#160;
                                                                        <xsl:value-of select="a:Zahtev_za_autorska_dela/a:Autor/sema:Adresa/sema:Drzava"/>&#160;,&#160;
                                                                        <xsl:value-of select="a:Zahtev_za_autorska_dela/a:Autor/a:Drzavljanstvo"/>
                                                                    </p>
                                                                </xsl:when>
                                                                <xsl:otherwise>
                                                                        <br/>
                                                                        Година смрти: <br/>
                                                                        <xsl:value-of select="a:Zahtev_za_autorska_dela/a:Autor/a:Godina_smrti"/>
                                                                </xsl:otherwise>
                                                            </xsl:choose>

                                                        </p>
                                                    </xsl:otherwise>
                                            </xsl:choose>
                                        </p>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        Подносилац је аутор.
                                    </xsl:otherwise>
                                </xsl:choose>
                            </p>
                        </td>
                    </tr>

                    <tr style="height: 100pt">
                        <td class="left_right" style="width: 100%;" colspan="2">
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                                9) Податак да ли је у питању ауторско дело створено у радном односу:
                                <br/>
                            </p>
                            <p style="padding-left: 10pt;">
                                <xsl:variable name="radni_odnos" select="a:Zahtev_za_autorska_dela/a:Autorsko_delo/a:Stvoreno_u_radnom_odnosu"/>
                                <xsl:choose>
                                    <xsl:when test="$radni_odnos=false">
                                        <p style="font-size: 8.5pt;padding-top: 6pt;padding-left: 7pt;line-height: 10pt;">
                                            Ne.
                                        </p>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        Dа.
                                    </xsl:otherwise>
                                </xsl:choose>
                            </p>
                        </td>
                    </tr>
                    <tr style="height: 100pt">
                        <td class="left_right" style="width: 100%;" colspan="2">
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                            10) Начин коришћења ауторског дела или намеравани начин коришћења ауторског дела:
                            </p>
                            <p style="padding-left: 10pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                                <xsl:value-of select="a:Zahtev_za_autorska_dela/a:Autorsko_delo/a:Nacin_koriscenja"/>
                            </p>
                        </td>
                    </tr>
                    <tr class="left_right" style="height: 190pt">
                        <td  style="width: 100%; border-left-style: solid; border-left-width: 1pt;border-bottom-style: solid; border-bottom-width: 1pt;">
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                            11)
                            </p>
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                            </p>
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                            </p>
                        </td>
                        <td  style="width: 100%;border-right-style: solid; border-right-width: 1pt;border-bottom-style: solid; border-bottom-width: 1pt;">
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: right;font-size:11pt;margin-right:10pt;">
                            _________________________________
                            </p>
                            <p class = "s5" style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: right;font-size:11pt;margin-right:10pt;">
                                Подносилац пријаве, носилац права
                            </p>
                            <p  style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: right;font-size:11pt;margin-right:10pt;">
                                (место за потпис физичког лица, односно потпис
                                заступника правног лица или овлашћеног представника
                                у правном лицу)*
                            </p>
                        </td>
                    </tr>
                </table>
                <table style="border-collapse: collapse; margin-left: 19pt;width: 95%;margin-top:120px;"
                       cellspacing="0" class="center">
                    <tr style="height: 30pt">
                        <td class="left_right" style="width: 100%;border-top-style: solid; border-top-width: 1pt;" colspan="2">
                            <p style="padding-left: 5pt;padding-bottom: 10pt;line-height: 139%;text-align: left;font-size:11pt;">
                                12) Прилози који се подносе уз захтев:
                            </p>
                        </td>
                    </tr>
                    <tr style="height: 30pt">
                        <td class="left_right" style="width: 100%;" colspan="2">
                           <p style="font-size: 8pt;padding-top: 5pt;padding-bottom: 5pt; padding-left: 5pt;text-align: center;">
                                <h1 style="text-align: center;">
                                    ПОПУЊАВА ЗАВОД:
                                </h1>
                            </p>
                        </td>
                    </tr>
                    <tr style="height: 130pt">
                        <td class="left_right" style="width: 100%;padding-bottom:150pt;" colspan="2">
                            <p class="s5" style="padding-left: 5pt;padding-bottom:15pt;line-height: 139%;margin-bottom:15pt;text-align: left;font-size:11pt;">
                                Прилози уз пријаву:
                            </p>
                            <p style="padding-left: 5pt;line-height: 139%;text-align: left;font-size:11pt;">
                                <span class="s7" style="padding-right:20pt">☐ </span>опис ауторског дела (ако је дело поднето на оптичком диску);
                            </p>
                            <p style="padding-left: 5pt;line-height: 139%;text-align: left;font-size:11pt;">
                                <span class="s7" style="padding-right:20pt">☐ </span>пример ауторског дела (слика, видео запис, аудио запис)
                            </p>
                        </td>
                    </tr>
                    <tr style="height: 40pt">
                        <td  style="width: 100%;border-left-style: solid;
                    border-left-width: 1pt;" >

                        </td>
                        <td class="all_borders" style="width: 100%;padding-bottom:10pt;" >
                            <p style="margin-left: 25pt;margin-top:20pt;padding-top:10pt;line-height: 139%;text-align: left;font-size:15pt;">
                                Број пријаве:
                            </p>
                            <p style="margin-left: 25pt;margin-top:20pt;line-height: 139%;text-align: left;font-size:20pt;">
                                <b>A-</b>
                            </p>
                        </td>
                    </tr>
                    <tr style="height: 40pt">
                        <td  style="width: 100%;border-left-style: solid;
                    border-left-width: 1pt;border-bottom-style: solid;
                    border-bottom-width: 1pt;" >

                        </td>
                        <td class="all_borders" style="width: 100%;padding-bottom:10pt;" >
                            <p style="margin-left: 25pt;margin-top:20pt;line-height: 139%;text-align: left;font-size:15pt;">
                                Датум подношења:
                            </p>
                            <p style="margin-left: 5pt;padding-top:10pt;padding-bottom:10pt;line-height: 139%;text-align: left;font-size:15pt;height:25pt">

                            </p>
                        </td>
                    </tr>
                </table>
                Рубрике у захтеву А-1 које су означене са * морају да буду обавезно попуњене.
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
