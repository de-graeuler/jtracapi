package de.graeuler.jtracapi.xmlrpc.ticket;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.xmlrpc.XmlRpcException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import de.graeuler.jtracapi.model.ticket.Ticket;
import de.graeuler.jtracapi.model.ticket.TicketAction;
import de.graeuler.jtracapi.model.ticket.TicketActionList;
import de.graeuler.jtracapi.model.ticket.TicketField;
import de.graeuler.jtracapi.model.ticket.TicketFieldList;
import de.graeuler.jtracapi.xmlrpc.TracInterface;

public interface TracTicket extends TracInterface {

	/**
	 * Perform a ticket query, returning a list of ticket ID's. All queries will
	 * use stored settings for maximum number of results per page and paging
	 * options. Use max=n to define number of results to receive, and use page=n
	 * to page through larger result sets. Using max=0 will turn off paging and
	 * return all results.
	 */
	public List<Integer> query(String qstr) throws XmlRpcException;

	/**
	 * Returns a list of IDs of tickets that have changed since timestamp.
	 * 
	 * It is good to know, that trac stores the date in GMT. Date since is
	 * compared without any timezone conversions. This means, if you are in
	 * GMT+2 and it's 10:00 AM, to get all changes for the recent 5 minutes, you
	 * would have to pass 7:55 AM as Date since.
	 * 
	 * A simple way to do this is using JodaTime:
	 * 
	 * <pre>
	 * <code>
	 * import org.joda.time.*;
	 * 
	 * DateTime changesSince = DateTime.now(DateTimeZone.UTC).minusMinutes(2);
	 * List<Integer> l = ticket.getRecentChanges(changesSince.toLocalDateTime().toDate());
	 * </code>
	 * </pre>
	 */
	public List<Integer> getRecentChanges(Date since);

	/**
	 * Deprecated - will be removed. Replaced by getActions().
	 */
	@Deprecated
	public TicketActionList<TicketAction> getAvailableActions(int id);

	/**
	 * Returns the actions that can be performed on the ticket as a list of
	 * [action, label, hints, [input_fields]] elements, where input_fields is a
	 * list of [name, value, [options]] for any required action inputs.
	 */
	public TicketActionList<TicketAction> getActions(int id)
			throws XmlRpcException;

	/**
	 * Fetch a ticket. Returns [id, time_created, time_changed, attributes].
	 */
	public Ticket get(int id) throws XmlRpcException;

	/**
	 * Create a new ticket, returning the ticket ID. Overriding 'when' requires
	 * admin permission.
	 */
	public Integer create(String summary, String description)
			throws XmlRpcException;

	public Integer create(String summary, String description,
			Map<String, Object> attributes) throws XmlRpcException;

	public Integer create(String summary, String description,
			Map<String, Object> attributes, boolean notify)
			throws XmlRpcException;

	public Integer create(String summary, String description,
			Map<String, Object> attributes, boolean notify, Date when)
			throws XmlRpcException;

	/**
	 * Update a ticket, returning the new ticket in the same form as get().
	 * 'New-style' call requires two additional items in attributes: (1)
	 * 'action' for workflow support (including any supporting fields as
	 * retrieved by getActions()), (2) '_ts' changetime token for detecting
	 * update collisions (as received from get() or update() calls). Calling
	 * update without 'action' and '_ts' changetime token is deprecated, and
	 * will raise errors in a future version.
	 */
	@Deprecated
	public Ticket update(int id, String comment) throws XmlRpcException;

	public Ticket update(int id, String comment, Map<String, Object> attributes)
			throws XmlRpcException;

	public Ticket update(int id, String comment,
			Map<String, Object> attributes, boolean notify)
			throws XmlRpcException;

	public Ticket update(int id, String comment,
			Map<String, Object> attributes, boolean notify, String author)
			throws XmlRpcException;

	public Ticket update(int id, String comment,
			Map<String, Object> attributes, boolean notify, String author,
			Date when) throws XmlRpcException;

	/**
	 * Delete ticket with the given id.
	 */
	public int delete(int id) throws XmlRpcException;

	/**
	 * Return the changelog as a list of tuples of the form (time, author,
	 * field, oldvalue, newvalue, permanent). While the other tuple elements are
	 * quite self-explanatory, the permanent flag is used to distinguish
	 * collateral changes that are not yet immutable (like attachments,
	 * currently).
	 */
	public List<List<Object>> changeLog(int id, int when)
			throws XmlRpcException;

	/**
	 * Lists attachments for a given ticket. Returns (filename, description,
	 * size, time, author) for each attachment.
	 */
	public List<List<Object>> listAttachments(int ticket)
			throws XmlRpcException;

	/**
	 * returns the content of an attachment.
	 */
	public byte[] getAttachment(int ticket, String filename)
			throws XmlRpcException;

	/**
	 * Add an attachment, optionally (and defaulting to) overwriting an existing
	 * one. Returns filename.
	 */
	public String putAttachment(int ticket, String filename,
			String description, byte[] data, boolean replace)
			throws XmlRpcException;

	/**
	 * Delete an attachment.
	 */
	public boolean deleteAttachment(int ticket, String filename)
			throws XmlRpcException;

	/**
	 * Return a list of all ticket fields fields.
	 */
	public TicketFieldList<TicketField> getTicketFields();

}
