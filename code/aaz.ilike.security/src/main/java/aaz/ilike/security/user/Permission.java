package aaz.ilike.security.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:update"),
    ADMIN_DELETE("admin:delete"),
    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete"),
    USER_READ("user:read"),
    USER_UPDATE("user:update"),
    USER_DELETE("user:delete"),
    USER_CREATE("user:update")

    ;

    @Getter
    private final String permission;
}
