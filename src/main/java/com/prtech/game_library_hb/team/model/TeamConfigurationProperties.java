package com.prtech.game_library_hb.team.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("team")
public class TeamConfigurationProperties {
    private boolean allowMultipleTeamsFromTemplate;

    boolean isAllowMultipleTeamsFromTemplate() {
        return allowMultipleTeamsFromTemplate;
    }

    void setAllowMultipleTeamsFromTemplate(final boolean allowMultipleTeamsFromTemplate) {
        this.allowMultipleTeamsFromTemplate = allowMultipleTeamsFromTemplate;
    }
}
