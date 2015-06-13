package de.graeuler.jtracapi.xmlrpc.system;

import java.util.List;
import java.util.Map;

import de.graeuler.jtracapi.xmlrpc.TracInterface;

public interface TracSystem extends TracInterface{
	/**
	 * Takes an array of RPC calls encoded as structs of the form (in a
	 * Pythonish notation here): {'methodName': string, 'params': array}. For
	 * JSON-RPC multicall, signatures is an array of regular method call
	 * structs, and result is an array of return structures.
	 */
	public Object[] multicall(List<Map<String,Object>> signatures);

	/**
	 * This method returns a list of strings, one for each (non-system) method
	 * supported by the RPC server.
	 */
	List<String> listMethods();

	/**
	 * This method takes one parameter, the name of a method implemented by the
	 * RPC server. It returns a documentation string describing the use of that
	 * method. If no such string is available, an empty string is returned. The
	 * documentation string may contain HTML markup.
	 */
	String methodHelp(String method);

	/**
	 * This method takes one parameter, the name of a method implemented by the
	 * RPC server. It returns an array of possible signatures for this method. A
	 * signature is an array of types. The first of these types is the return
	 * type of the method, the rest are parameters.
	 */
	public List<Object> methodSignature(String method);

	/**
	 * Returns a list with three elements. First element is the epoch (0=Trac
	 * 0.10, 1=Trac 0.11 or higher). Second element is the major version number,
	 * third is the minor. Changes to the major version indicate API breaking
	 * changes, while minor version changes are simple additions, bug fixes,
	 * etc.
	 */
	public List<Integer> getAPIVersion();

}
