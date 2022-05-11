package Handlers;

import DataAccess.AuthtokenDAO;
import DataAccess.DataAccessException;
import DataAccess.Database;
import Models.Authtoken;
import Result.EventByIDResult;
import Result.EventResult;
import Services.EventService;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class EventHandler implements HttpHandler {
    private static final Database db = new Database();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;
        String respData = null;
        boolean resultSuccess = false;
        EventService service = new EventService();
        try {
            exchange.getRequestMethod();
            Headers reqHeaders = exchange.getRequestHeaders();
                if (reqHeaders.containsKey("Authorization")) {
                    String authToken = reqHeaders.getFirst("Authorization");
                    db.openConnection();
                    AuthtokenDAO authTokenDAO = db.getAuthtokenDAO();
                    Authtoken token = authTokenDAO.find(authToken);
                    db.closeConnection(true);
                    String uri = exchange.getRequestURI().toString();
                    Gson gson = new Gson();

                    if (uri.equals("/event")) {
                        EventResult result = service.events(authToken);
                        resultSuccess = result.isSuccess();
                        respData = gson.toJson(result);
                    } else if (uri.startsWith("/event/")) {
                        EventByIDResult result = service.eventByID(uri.substring(7), authToken);
                        resultSuccess = result.getSuccess();
                        respData = gson.toJson(result);
                    } else {
                        respData = "Invalid request";
                    }

                    if (token != null && resultSuccess ) {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                        OutputStream respBody = exchange.getResponseBody();
                        writeString(respData, respBody);
                        respBody.close();
                        success = true;
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
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }

    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}
