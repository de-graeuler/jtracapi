package de.graeuler.jtracapi.xmlrpc.wiki;

import java.util.Date;
import java.util.List;
import java.util.Map;

import de.graeuler.jtracapi.model.wiki.WikiPageInfo;
import de.graeuler.jtracapi.xmlrpc.TracInterface;

public interface TracWiki extends TracInterface {
	/**
	 * Get list of changed pages since timestamp
	 */
	public List<Map<String, Object>> getRecentChanges(Date since);

	/**
	 * Returns the version of the Trac API.
	 */
	public int getRPCVersionSupported();

	/**
	 * Get the raw Wiki text of page, latest version.
	 */
	public String getPage(String pagename, int version);
	public String getPage(String pagename);

	/**
	 * Get the raw Wiki text of page, latest version.
	 */
	String getPageVersion(String pagename, int version);
	String getPageVersion(String pagename);

	/**
	 * Return page in rendered HTML, latest version.
	 */
	String getPageHTML(String pagename, int version);
	String getPageHTML(String pagename);

	/**
	 * Return page in rendered HTML, latest version.
	 */
	String getPageHTMLVersion(String pagename, int version);
	String getPageHTMLVersion(String pagename);

	/**
	 * Returns a list of all pages. The result is an array of utf8 pagenames.
	 */
	List<String> getAllPages();

	/**
	 * Returns information about the given page.
	 */
	WikiPageInfo getPageInfo(String pagename, int version);
	WikiPageInfo getPageInfo(String pagename);

	/**
	 * Returns information about the given page.
	 */
	WikiPageInfo getPageInfoVersion(String pagename, int version);
	WikiPageInfo getPageInfoVersion(String pagename);

	/**
	 * writes the content of the page.
	 */
	boolean putPage(String pagename, String content,
			Map<String, Object> attributes);

	/**
	 * Lists attachments on a given page.
	 */
	List<String> listAttachments(String pagename);

	/**
	 * returns the content of an attachment.
	 */
	byte[] getAttachment(String path);

	/**
	 * (over)writes an attachment. Returns True if successful. This method is
	 * compatible with WikiRPC. putAttachmentEx has a more extensive set of
	 * (Trac-specific) features.
	 */
	boolean putAttachment(String path, byte[] data);

	/**
	 * Attach a file to a Wiki page. Returns the (possibly transformed) filename
	 * of the attachment. Use this method if you don't care about WikiRPC
	 * compatibility.
	 */
	boolean putAttachmentEx(String pagename, String filename,
			String description, byte[] data, boolean replace);

	/**
	 * Delete a Wiki page (all versions) or a specific version by including an
	 * optional version number. Attachments will also be deleted if page no
	 * longer exists. Returns True for success.
	 */
	boolean deletePage(String name, int version);
	boolean deletePage(String name);

	/**
	 * Delete an attachment.
	 */
	boolean deleteAttachment(String path);

	/**
	 * Render arbitrary Wiki text as HTML.
	 */
	String wikiToHtml(String text);

}
