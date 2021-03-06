
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
@WebService(name = "UserGetSoapServiceImpl", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UserGetSoapServiceImpl {


    /**
     * 
     * @param arg0
     * @return
     *     returns pl.lodz.p.it.tks.soap.client.UserSoap
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getUser", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.GetUser")
    @ResponseWrapper(localName = "getUserResponse", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.GetUserResponse")
    @Action(input = "http://client.soap.tks.it.p.lodz.pl/UserGetSoapServiceImpl/getUserRequest", output = "http://client.soap.tks.it.p.lodz.pl/UserGetSoapServiceImpl/getUserResponse")
    public UserSoap getUser(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @return
     *     returns java.util.List<pl.lodz.p.it.tks.soap.client.ClientSoap>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getClients", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.GetClients")
    @ResponseWrapper(localName = "getClientsResponse", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.GetClientsResponse")
    @Action(input = "http://client.soap.tks.it.p.lodz.pl/UserGetSoapServiceImpl/getClientsRequest", output = "http://client.soap.tks.it.p.lodz.pl/UserGetSoapServiceImpl/getClientsResponse")
    public List<ClientSoap> getClients();

    /**
     * 
     * @return
     *     returns java.util.List<pl.lodz.p.it.tks.soap.client.ManagerSoap>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getManagers", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.GetManagers")
    @ResponseWrapper(localName = "getManagersResponse", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.GetManagersResponse")
    @Action(input = "http://client.soap.tks.it.p.lodz.pl/UserGetSoapServiceImpl/getManagersRequest", output = "http://client.soap.tks.it.p.lodz.pl/UserGetSoapServiceImpl/getManagersResponse")
    public List<ManagerSoap> getManagers();

    /**
     * 
     * @return
     *     returns java.util.List<pl.lodz.p.it.tks.soap.client.AdminSoap>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAdmins", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.GetAdmins")
    @ResponseWrapper(localName = "getAdminsResponse", targetNamespace = "http://client.soap.tks.it.p.lodz.pl/", className = "pl.lodz.p.it.tks.soap.client.GetAdminsResponse")
    @Action(input = "http://client.soap.tks.it.p.lodz.pl/UserGetSoapServiceImpl/getAdminsRequest", output = "http://client.soap.tks.it.p.lodz.pl/UserGetSoapServiceImpl/getAdminsResponse")
    public List<AdminSoap> getAdmins();

}
