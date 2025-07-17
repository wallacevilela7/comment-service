package tech.wvs.commentsms.api.client;

import tech.wvs.commentsms.api.web.ModerationInput;
import tech.wvs.commentsms.api.web.ModerationOutput;

public interface ModerationServiceClient {

    ModerationOutput validadeComment(ModerationInput dto);
}
