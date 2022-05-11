package Handlers;

import DataAccess.AuthtokenDAO;
import DataAccess.DataAccessException;
import DataAccess.Database;
import Models.Authtoken;
import Result.PersonResult;
import Result.PersonsResult;
import Services.PersonService;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;

public class PersonHandler implements HttpHandler {
    private static final Database db = new Database();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;
        String respData = null;
        boolean resultSuccess = false;
        try {
            if (exchange.getRequestMethod().equalsIgnoreCase("get")) {

                Headers reqHeaders = exchange.getRequestHeaders();
                if (reqHeaders.containsKey("Authorization")) {
                    String authToken = reqHeaders.getFirst("Authorization");

                    db.openConnection();
                    AuthtokenDAO authTokenDAO = db.getAuthtokenDAO();
                    Authtoken token = authTokenDAO.find(authToken);
                    db.closeConnection(true);
                    PersonService personService = new PersonService();
                    String uri = exchange.getRequestURI().toString();
                    Gson gson = new Gson();

                    if (uri.equals("/person")) {
                        PersonsResult result = personService.persons(authToken);
                        resultSuccess = result.getSuccess();
                        respData = gson.toJson(result);
                    } else if (uri.startsWith("/person/")) {
                        PersonResult result = personService.person(uri.substring(8), authToken);
                        resultSuccess = result.getSuccess();
                        respData = gson.toJson(result);
                    }

                    if (token != null && resultSuccess) {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                        OutputStream respBody = exchange.getResponseBody();
                        writeString(respData, respBody);
                        respBody.close();

                        success = true;
                    }
                }
            }
            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                OutputStream respBody = exchange.getResponseBody();
                writeString(respData, respBody);
                exchange.getResponseBody().close();
            }
        } catch (IOException | DataAccessException e) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
                OutputStream respBody = exchange.getResponseBody();
                writeString(respData, respBody);
                exchange.getResponseBody().close();
                e.printStackTrace();
            }
    }
    /*
		The writeString method shows how to write a String to an OutputStream.
	*/
    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}
