/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package com.x.report.core.entity;

import com.x.base.core.entity.SliceJpaObject_;
import java.lang.String;
import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=com.x.report.core.entity.Report_I_Detail.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Sun Sep 02 18:44:17 CST 2018")
public class Report_I_Detail_ extends SliceJpaObject_  {
    public static volatile SingularAttribute<Report_I_Detail,String> authors;
    public static volatile SingularAttribute<Report_I_Detail,Date> createTime;
    public static volatile SingularAttribute<Report_I_Detail,String> id;
    public static volatile SingularAttribute<Report_I_Detail,String> opinions;
    public static volatile SingularAttribute<Report_I_Detail,String> readers;
    public static volatile SingularAttribute<Report_I_Detail,String> reportId;
    public static volatile SingularAttribute<Report_I_Detail,String> sequence;
    public static volatile SingularAttribute<Report_I_Detail,String> title;
    public static volatile SingularAttribute<Report_I_Detail,Date> updateTime;
    public static volatile SingularAttribute<Report_I_Detail,String> workflowLog;
}
