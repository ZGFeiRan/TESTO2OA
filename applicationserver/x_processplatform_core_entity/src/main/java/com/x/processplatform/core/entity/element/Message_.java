/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package com.x.processplatform.core.entity.element;

import com.x.base.core.entity.SliceJpaObject_;
import java.lang.Boolean;
import java.lang.String;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=com.x.processplatform.core.entity.element.Message.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Sun Sep 02 18:44:03 CST 2018")
public class Message_ extends SliceJpaObject_  {
    public static volatile SingularAttribute<Message,String> afterArriveScript;
    public static volatile SingularAttribute<Message,String> afterArriveScriptText;
    public static volatile SingularAttribute<Message,String> afterExecuteScript;
    public static volatile SingularAttribute<Message,String> afterExecuteScriptText;
    public static volatile SingularAttribute<Message,String> afterInquireScript;
    public static volatile SingularAttribute<Message,String> afterInquireScriptText;
    public static volatile SingularAttribute<Message,String> alias;
    public static volatile SingularAttribute<Message,Boolean> allowReroute;
    public static volatile SingularAttribute<Message,Boolean> allowRerouteTo;
    public static volatile SingularAttribute<Message,String> beforeArriveScript;
    public static volatile SingularAttribute<Message,String> beforeArriveScriptText;
    public static volatile SingularAttribute<Message,String> beforeExecuteScript;
    public static volatile SingularAttribute<Message,String> beforeExecuteScriptText;
    public static volatile SingularAttribute<Message,String> beforeInquireScript;
    public static volatile SingularAttribute<Message,String> beforeInquireScriptText;
    public static volatile SingularAttribute<Message,String> description;
    public static volatile SingularAttribute<Message,String> extension;
    public static volatile SingularAttribute<Message,String> form;
    public static volatile SingularAttribute<Message,String> id;
    public static volatile SingularAttribute<Message,String> name;
    public static volatile SingularAttribute<Message,String> position;
    public static volatile SingularAttribute<Message,String> process;
    public static volatile ListAttribute<Message,String> readDataPathList;
    public static volatile SingularAttribute<Message,String> readDuty;
    public static volatile ListAttribute<Message,String> readIdentityList;
    public static volatile SingularAttribute<Message,String> readScript;
    public static volatile SingularAttribute<Message,String> readScriptText;
    public static volatile ListAttribute<Message,String> readUnitList;
    public static volatile ListAttribute<Message,String> reviewDataPathList;
    public static volatile SingularAttribute<Message,String> reviewDuty;
    public static volatile ListAttribute<Message,String> reviewIdentityList;
    public static volatile SingularAttribute<Message,String> reviewScript;
    public static volatile SingularAttribute<Message,String> reviewScriptText;
    public static volatile ListAttribute<Message,String> reviewUnitList;
    public static volatile SingularAttribute<Message,String> route;
    public static volatile SingularAttribute<Message,String> script;
}
