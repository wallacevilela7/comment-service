package tech.wvs.commentsms.api.web;

public record ModerationOutput(Boolean approved,
                               String reason) {
}
