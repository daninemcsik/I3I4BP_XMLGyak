<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<h2>Rendszámok</h2>
				<xsl:apply-templates/>
			</body>
		</html>
	</xsl:template>
	
	<xsl:template match="autok">
		Rendszám: <xsl:value-of selec="."/>
		<br/>
	</xsl:template>
</xsl:stylesheet>