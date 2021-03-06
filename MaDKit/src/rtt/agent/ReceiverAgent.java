package rtt.agent;

import lombok.extern.slf4j.Slf4j;
import madkit.kernel.Agent;
import madkit.kernel.Message;

import static rtt.agent.Society.COMMUNITY;
import static rtt.agent.Society.ROLE_RECEIVER;


/* To exchange messages, agents have to exist in an artificial society. That is, agents have to create communities
 * containing groups and take roles within to interact with others. Doing so, agents get agent addresses which could be
 * used to send them messages. Here, two agents take a role and ping the other one.
 */
@Slf4j
public class ReceiverAgent extends Agent {
    static int group = 0;

    @Override
    protected void activate() {
        String GROUP = String.valueOf(group++);
        createGroup(COMMUNITY, GROUP);
        requestRole(COMMUNITY, GROUP, ROLE_RECEIVER);
    }


    @Override
    protected void live() {

        while (true) {
            Message m = waitNextMessage();
            if (m != null) {
                sendReply(m, new Message());
            }
        }
    }
}