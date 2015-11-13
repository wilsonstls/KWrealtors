package kwrealtors;


/** 
  * User: wilsonstls
  * Date: 9/16/15 
  *Factory design pattern implementation based on the user's job type
*/

public class PortalFactory {

    static  Portal makePortalType(String portalType) {


        switch (portalType) {
            case "A":
                return new AgentPortal();
            case "C":
                return new ClericalPortal();
            case "M":
                return new ManagerPortal();

        }

        throw new IllegalArgumentException(" Error - No portal available!!");
    }
}