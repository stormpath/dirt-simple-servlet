/*
 * Copyright 2014 Stormpath, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stormpath.sdk.examples.servlet;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.application.Application;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WelcomeController extends HttpServlet {

    protected Application getApplication(HttpServletRequest req) {
        return (Application)req.getAttribute(Application.class.getName());
    }

    protected Account getAccount(HttpServletRequest req) {
        return (Account)req.getAttribute(Account.class.getName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Application app = getApplication(req);
        Account acc = getAccount(req);

        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head><style>body { font-family: arial, sans-serif; }</style></head>");
        sb.append("<body>");
        sb.append("<h1>Welcome</h1>");
        sb.append("<h3>App Name: " + app.getName() + "</h3>");
        sb.append("<h3>App Description: " + app.getDescription() + "</h3>");

        if (acc == null) {
            // not logged in
            sb.append("<p><h4>Click <a href=\"/login\">here</a> to log in.</h4></p>");
            sb.append("<p><h4>Click <a href=\"/register\">here</a> to register.</h4></p>");
        } else {
            // logged in
            sb.append("<p><h4>Welcome, " + acc.getFullName() + "!</h4></p>");
            sb.append("<p><h4>Click <a href=\"/logout\">here</a> to log out.</h4></p>");
        }

        sb.append("</body></html>");

        PrintWriter out = resp.getWriter();
        out.println(sb.toString());
    }
}
