/** 
 *  Generated by OpenJPA MetaModel Generator Tool.
**/

package com.x.cms.core.entity;

import com.x.base.core.entity.SliceJpaObject_;
import java.lang.Boolean;
import java.lang.String;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;

@javax.persistence.metamodel.StaticMetamodel
(value=com.x.cms.core.entity.CategoryInfo.class)
@javax.annotation.Generated
(value="org.apache.openjpa.persistence.meta.AnnotationProcessor6",date="Sun Sep 02 18:43:27 CST 2018")
public class CategoryInfo_ extends SliceJpaObject_  {
    public static volatile SingularAttribute<CategoryInfo,Boolean> allPeoplePublish;
    public static volatile SingularAttribute<CategoryInfo,Boolean> allPeopleView;
    public static volatile SingularAttribute<CategoryInfo,String> appId;
    public static volatile SingularAttribute<CategoryInfo,String> appName;
    public static volatile SingularAttribute<CategoryInfo,String> categoryAlias;
    public static volatile SingularAttribute<CategoryInfo,String> categoryIcon;
    public static volatile SingularAttribute<CategoryInfo,String> categoryMemo;
    public static volatile SingularAttribute<CategoryInfo,String> categoryName;
    public static volatile SingularAttribute<CategoryInfo,String> categorySeq;
    public static volatile SingularAttribute<CategoryInfo,String> creatorIdentity;
    public static volatile SingularAttribute<CategoryInfo,String> creatorPerson;
    public static volatile SingularAttribute<CategoryInfo,String> creatorTopUnitName;
    public static volatile SingularAttribute<CategoryInfo,String> creatorUnitName;
    public static volatile SingularAttribute<CategoryInfo,String> defaultViewId;
    public static volatile SingularAttribute<CategoryInfo,String> defaultViewName;
    public static volatile SingularAttribute<CategoryInfo,String> description;
    public static volatile SingularAttribute<CategoryInfo,String> documentType;
    public static volatile SingularAttribute<CategoryInfo,String> formId;
    public static volatile SingularAttribute<CategoryInfo,String> formName;
    public static volatile SingularAttribute<CategoryInfo,String> id;
    public static volatile SingularAttribute<CategoryInfo,String> importViewAppId;
    public static volatile SingularAttribute<CategoryInfo,String> importViewId;
    public static volatile SingularAttribute<CategoryInfo,String> importViewName;
    public static volatile ListAttribute<CategoryInfo,String> manageableGroupList;
    public static volatile ListAttribute<CategoryInfo,String> manageablePersonList;
    public static volatile ListAttribute<CategoryInfo,String> manageableUnitList;
    public static volatile SingularAttribute<CategoryInfo,String> parentId;
    public static volatile ListAttribute<CategoryInfo,String> publishableGroupList;
    public static volatile ListAttribute<CategoryInfo,String> publishablePersonList;
    public static volatile ListAttribute<CategoryInfo,String> publishableUnitList;
    public static volatile SingularAttribute<CategoryInfo,String> readFormId;
    public static volatile SingularAttribute<CategoryInfo,String> readFormName;
    public static volatile ListAttribute<CategoryInfo,String> viewableGroupList;
    public static volatile ListAttribute<CategoryInfo,String> viewablePersonList;
    public static volatile ListAttribute<CategoryInfo,String> viewableUnitList;
    public static volatile SingularAttribute<CategoryInfo,String> workflowAppId;
    public static volatile SingularAttribute<CategoryInfo,String> workflowAppName;
    public static volatile SingularAttribute<CategoryInfo,String> workflowFlag;
    public static volatile SingularAttribute<CategoryInfo,String> workflowName;
    public static volatile SingularAttribute<CategoryInfo,String> workflowType;
}
