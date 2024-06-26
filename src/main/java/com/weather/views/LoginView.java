package com.weather.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.weather.views.weather.WeatherView;

@Route("login")
@PageTitle("Login | Weather App")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {
    private final LoginForm login = new LoginForm();

    public LoginView(){
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("login");
        login.setI18n(createLoginI18n());

        // Handle login success
        login.addLoginListener(e -> {
            if (authenticate(e.getUsername(), e.getPassword())) {
                UI.getCurrent().navigate(WeatherView.class);
            } else {
                login.setError(true);
            }
        });

        add(login, new RouterLink("Register", RegisterView.class));
    }

    private LoginI18n createLoginI18n() {
        final LoginI18n i18n = LoginI18n.createDefault();
        i18n.getForm().setUsername("User Name");
        i18n.getForm().setSubmit("Sign in");
        i18n.getForm().setTitle("Sign in to Weather App");
        i18n.getForm().setPassword("Password");
        i18n.getErrorMessage().setTitle("Invalid username or password");
        i18n.getErrorMessage().setMessage("Check the username and password");
        return i18n;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        // Inform the user about an authentication error
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }

    // Dummy authentication method
    private boolean authenticate(String username, String password) {
        // Implement your authentication logic here
        // Return true if authentication is successful, false otherwise
        return "user".equals(username) && "password".equals(password);
    }
}
