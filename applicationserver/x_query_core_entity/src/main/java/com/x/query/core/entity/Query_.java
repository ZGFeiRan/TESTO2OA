/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package com.x.query.core.entity;

import com.x.base.core.entity.SliceJpaObject_;
import java.lang.String;
import java.util.Date;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=com.x.query.core.entity.Query.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Sun Sep 02 18:44:14 CST 2018")
public class Query_ extends SliceJpaObject_  {
    public static volatile SingularAttribute<Query,String> alias;
    public static volatile ListAttribute<Query,String> availableIdentityList;
    public static volatile ListAttribute<Query,String> availableUnitList;
    public static volatile ListAttribute<Query,String> controllerList;
    public static volatile SingularAttribute<Query,String> creatorPerson;
    public static volatile SingularAttribute<Query,String> description;
    public static volatile SingularAttribute<Query,String> icon;
    public static volatile SingularAttribute<Query,String> iconHue;
    public static volatile SingularAttribute<Query,String> id;
    public static volatile SingularAttribute<Query,String> lastUpdatePerson;
    public static volatile SingularAttribute<Query,Date> lastUpdateTime;
    public static volatile SingularAttribute<Query,String> name;
    public static volatile SingularAttribute<Query,String> queryCategory;
}
