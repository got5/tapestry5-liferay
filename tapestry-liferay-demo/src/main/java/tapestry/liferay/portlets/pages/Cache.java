package tapestry.liferay.portlets.pages;

public class Cache {
	
	public String getCodeSnippet() {
		return "<div t:type=\"cache/Container\">\n\t...TML content to cache...\n</div>";
	}
	
	public String getPomDependency() {
		return "<dependency>\n\t<groupId>uk.co.ioko</groupId>\n\t<artifactId>tapestry-caching</artifactId>\n\t<version>3.6.1</version>\n</dependency>";
	}
}
