<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html"/>
	<xsl:template match="/">
		<html>
			<body>
				<table border="1">
					<tr bgcolor="#008000">
						<th>ID</th>
						<th>Vezeteknev</th>
						<th>Keresztnev</th>
						<th>Becenev</th>
						<th>Fizetes</th>
						<th>Fokozat</th>
					</tr>
					<xsl:for-each select="class/student">
					<tr>
						<td><xsl:value-of select="class/student/@id"/></td>
						<td><xsl:value-of select="class/student/vezeteknev"/></td>
						<td><xsl:value-of select="class/student/keresztnev"/></td>
						<td><xsl:value-of select="class/student/becenev"/></td>
						<td><xsl:value-of select="class/student/fizetes"/></td>
					</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>