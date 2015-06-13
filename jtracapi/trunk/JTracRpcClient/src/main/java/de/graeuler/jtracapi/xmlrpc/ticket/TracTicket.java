package de.graeuler.jtracapi.xmlrpc.ticket;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
	public List<Integer> query(String qstr);

	/**
	 * Returns a list of IDs of tickets that have changed since timestamp.
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
	public TicketActionList<TicketAction> getActions(int id);

	/**
	 * Fetch a ticket. Returns [id, time_created, time_changed, attributes].
	 */
	public Ticket get(int id);

	/**
	 * Create a new ticket, returning the ticket ID. Overriding 'when' requires
	 * admin permission.
	 */
	public int create(String summary, String description,
			Map<String, Object> attributes, boolean notify, Date when);

	/**
	 * Update a ticket, returning the new ticket in the same form as get().
	 * 'New-style' call requires two additional items in attributes: (1)
	 * 'action' for workflow support (including any supporting fields as
	 * retrieved by getActions()), (2) '_ts' changetime token for detecting
	 * update collisions (as received from get() or update() calls). Calling
	 * update without 'action' and '_ts' changetime token is deprecated, and
	 * will raise errors in a future version.
	 */
	public Ticket update(int id, String comment,
			Map<String, Object> attributes, boolean notify, String author,
			Date when);

	/**
	 * Delete ticket with the given id.
	 */
	public int delete(int id);

	/**
	 * Return the changelog as a list of tuples of the form (time, author,
	 * field, oldvalue, newvalue, permanent). While the other tuple elements are
	 * quite self-explanatory, the permanent flag is used to distinguish
	 * collateral changes that are not yet immutable (like attachments,
	 * currently).
	 */
	public List<List<Object>> changeLog(int id, int when);

	/**
	 * Lists attachments for a given ticket. Returns (filename, description,
	 * size, time, author) for each attachment.
	 */
	public List<List<Object>> listAttachments(int ticket);

	/**
	 * returns the content of an attachment.
	 */
	public byte[] getAttachment(int ticket, String filename);

	/**
	 * Add an attachment, optionally (and defaulting to) overwriting an existing
	 * one. Returns filename.
	 */
	public String putAttachment(int ticket, String filename,
			String description, byte[] data, boolean replace);

	/**
	 * Delete an attachment.
	 */
	public boolean deleteAttachment(int ticket, String filename);

	/**
	 * Return a list of all ticket fields fields.
	 */
	public TicketFieldList<TicketField> getTicketFields();

}
