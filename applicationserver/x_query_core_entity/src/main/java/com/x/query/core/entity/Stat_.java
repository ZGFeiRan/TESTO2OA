/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package com.x.query.core.entity;

import com.x.base.core.entity.SliceJpaObject_;
import java.lang.String;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=com.x.query.core.entity.Stat.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Sun Sep 02 18:44:14 CST 2018")
public class Stat_ extends SliceJpaObject_  {
    public static volatile SingularAttribute<Stat,String> alias;
    public static volatile ListAttribute<Stat,String> availableIdentityList;
    public static volatile ListAttribute<Stat,String> availableUnitList;
    public static volatile SingularAttribute<Stat,String> data;
    public static volatile SingularAttribute<Stat,String> description;
    public static volatile SingularAttribute<Stat,String> id;
    public static volatile SingularAttribute<Stat,String> name;
    public static volatile SingularAttribute<Stat,String> query;
    public static volatile SingularAttribute<Stat,String> view;
}
