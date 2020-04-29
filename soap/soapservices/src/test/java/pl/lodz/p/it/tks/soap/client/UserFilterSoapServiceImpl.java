
package pl.lodz.p.it.tks.soap.client;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "UserFilterSoapServiceImpl", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UserFilterSoapServiceImpl {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<pl.lodz.p.it.tks.soap.client.AdminSoap>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "filterAdmins", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.FilterAdmins")
    @ResponseWrapper(localName = "filterAdminsResponse", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.FilterAdminsResponse")
    @Action(input = "http://client.soap.tks.it.p.lodz.pl/UserFilterSoapServiceImpl/filterAdminsRequest", output = "http://client.soap.tks.it.p.lodz.pl/UserFilterSoapServiceImpl/filterAdminsResponse")
    public List<AdminSoap> filterAdmins(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<pl.lodz.p.it.tks.soap.client.ManagerSoap>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "filterManagers", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.FilterManagers")
    @ResponseWrapper(localName = "filterManagersResponse", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.FilterManagersResponse")
    @Action(input = "http://client.soap.tks.it.p.lodz.pl/UserFilterSoapServiceImpl/filterManagersRequest", output = "http://client.soap.tks.it.p.lodz.pl/UserFilterSoapServiceImpl/filterManagersResponse")
    public List<ManagerSoap> filterManagers(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<pl.lodz.p.it.tks.soap.client.ClientSoap>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "filterClients", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.FilterClients")
    @ResponseWrapper(localName = "filterClientsResponse", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.FilterClientsResponse")
    @Action(input = "http://client.soap.tks.it.p.lodz.pl/UserFilterSoapServiceImpl/filterClientsRequest", output = "http://client.soap.tks.it.p.lodz.pl/UserFilterSoapServiceImpl/filterClientsResponse")
    public List<ClientSoap> filterClients(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}