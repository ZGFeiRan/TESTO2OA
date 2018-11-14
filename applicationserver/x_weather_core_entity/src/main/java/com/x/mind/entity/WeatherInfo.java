package com.x.mind.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.StringUtils;
import org.apache.openjpa.persistence.PersistentCollection;
import org.apache.openjpa.persistence.jdbc.ContainerTable;
import org.apache.openjpa.persistence.jdbc.ElementColumn;
import org.apache.openjpa.persistence.jdbc.ElementIndex;
import org.apache.openjpa.persistence.jdbc.Index;

import com.x.base.core.entity.AbstractPersistenceProperties;
import com.x.base.core.entity.JpaObject;
import com.x.base.core.entity.SliceJpaObject;
import com.x.base.core.entity.annotation.CheckPersist;
import com.x.base.core.entity.annotation.ContainerEntity;
import com.x.base.core.project.annotation.FieldDescribe;

import com.x.attendance.entity.PersistenceProperties;

/**
 * 天气信息表
 * 
 * @author O2LEE
 */
@ContainerEntity
@Entity
@Table(name = PersistenceProperties.AttendanceDetailMobile.table, uniqueConstraints = @UniqueConstraint(name = PersistenceProperties.AttendanceDetailMobile.table + JpaObject.IndexNameMiddle + JpaObject.DefaultUniqueConstraintSuffix, columnNames = { JpaObject.IDCOLUMN, JpaObject.CREATETIMECOLUMN, JpaObject.UPDATETIMECOLUMN, JpaObject.SEQUENCECOLUMN }))
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class WeatherInfo extends SliceJpaObject {

	private static final long serialVersionUID = 3856138316794473794L;
	private static final String TABLE = com.x.mind.entity.PersistenceProperties.MindBaseInfo.table;

	@FieldDescribe("自动生成，数据库主键")
	@Id
	@Column(name = "xid", length = JpaObject.length_id)
	private String id = createId();

	@FieldDescribe("自动生成，创建时间.")
	@Index(name = TABLE + "_createTime")
	@Column(name = "xcreateTime")
	private Date createTime;

	@FieldDescribe("自动生成，修改时间.")
	@Index(name = TABLE + "_updateTime")
	@Column(name = "xupdateTime")
	private Date updateTime;

	@FieldDescribe("自动生成，列表序号, 由创建时间以及ID组成.")
	@Column(name = "xsequence", length = AbstractPersistenceProperties.length_sequence)
	@Index(name = TABLE + "_sequence")
	private String sequence;

	/**
	 * 获取记录ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置记录ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取信息创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置信息创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取信息更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 设置信息更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 获取信息记录排序号
	 */
	public String getSequence() {
		return sequence;
	}

	/**
	 * 设置信息记录排序号
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public void onPersist() throws Exception {
		this.creator_sequence = StringUtils.join(this.creator, sequence);
		this.folder_sequence = StringUtils.join(this.getFolderId(), sequence);
		this.creatorUnit_sequence = StringUtils.join(this.getCreatorUnit(), sequence);
		this.shared_sequence = StringUtils.join(this.getShared().toString(), sequence);
	}
	
	/*
	 * =============================================================================
	 * ===== 以上为 JpaObject 默认字段
	 * =============================================================================
	 * =====
	 */

	/*
	 * =============================================================================
	 * ===== 以下为具体不同的业务及数据表字段要求
	 * =============================================================================
	 * =====
	 */
	public static String[] FLAGS = new String[] { "id" };

	public static final String name_FIELDNAME = "name";
	@FieldDescribe("脑图名称")
	@Column( length = JpaObject.length_255B, name = ColumnNamePrefix + name_FIELDNAME )
	@Index( name = TABLE + IndexNameMiddle + name_FIELDNAME )
	@CheckPersist( allowEmpty = false)
	private String name = "";

	public static final String folderId_FIELDNAME = "folderId";
	@FieldDescribe("所属目录ID")
	@Column( length = JpaObject.length_id, name = ColumnNamePrefix + folderId_FIELDNAME )
	@Index( name = TABLE + IndexNameMiddle + folderId_FIELDNAME )
	@CheckPersist( allowEmpty = false)
	private String folderId = "";	

	public static final String description_FIELDNAME = "description";
	@FieldDescribe("备注信息")
	@Column( length = JpaObject.length_255B, name = ColumnNamePrefix + description_FIELDNAME )
	@CheckPersist( allowEmpty = true)
	private String description = "";

	public static final String creator_FIELDNAME = "creator";
	@FieldDescribe("自动生成，创建者")
	@Column( length = JpaObject.length_255B, name = ColumnNamePrefix + creator_FIELDNAME )
	@Index( name = TABLE + IndexNameMiddle + creator_FIELDNAME )
	@CheckPersist( allowEmpty = false)
	private String creator = "";

	public static final String creatorUnit_FIELDNAME= "creatorUnit";
	@FieldDescribe("自动生成，创建者所属组织")
	@Column( length = JpaObject.length_255B, name = ColumnNamePrefix + creatorUnit_FIELDNAME )
	@CheckPersist( allowEmpty = false)
	private String creatorUnit = "";
	
	public static final String shared_FIELDNAME= "shared";
	@FieldDescribe("自动生成，是否已经分享")
	@Column( name = ColumnNamePrefix + shared_FIELDNAME )
	@Index( name = TABLE + IndexNameMiddle + shared_FIELDNAME )
	@CheckPersist( allowEmpty = false)
	private Boolean shared = false;	

	public static final String cooperative_FIELDNAME= "cooperative";
	@FieldDescribe("自动生成，是否协作模式")
	@Column( name = ColumnNamePrefix + cooperative_FIELDNAME )
	@CheckPersist( allowEmpty = false)
	private Boolean cooperative = false;

	public static final String sharePersonList_FIELDNAME= "sharePersonList";
	@FieldDescribe("共享人员")
	@PersistentCollection(fetch = FetchType.EAGER)
	@OrderColumn(name = AbstractPersistenceProperties.orderColumn)
	@ContainerTable(name = TABLE + ContainerTableNameMiddle + sharePersonList_FIELDNAME, joinIndex = @Index(name = TABLE
			+ IndexNameMiddle + sharePersonList_FIELDNAME + JoinIndexNameSuffix))
	@ElementColumn(length = AbstractPersistenceProperties.organization_name_length, 
			name = ColumnNamePrefix + sharePersonList_FIELDNAME)
	@ElementIndex(name = TABLE + IndexNameMiddle + sharePersonList_FIELDNAME + ElementIndexNameSuffix)
	@CheckPersist(allowEmpty = true)
	private List<String> sharePersonList;

	public static final String shareUnitList_FIELDNAME= "shareUnitList";
	@FieldDescribe("共享组织")
	@PersistentCollection(fetch = FetchType.EAGER)
	@OrderColumn(name = AbstractPersistenceProperties.orderColumn)
	@ContainerTable(name = TABLE + ContainerTableNameMiddle + shareUnitList_FIELDNAME, joinIndex = @Index(name = TABLE
			+ IndexNameMiddle + shareUnitList_FIELDNAME + JoinIndexNameSuffix))
	@ElementColumn(length = AbstractPersistenceProperties.organization_name_length, 
			name = ColumnNamePrefix + shareUnitList_FIELDNAME)
	@ElementIndex(name = TABLE + IndexNameMiddle + shareUnitList_FIELDNAME + ElementIndexNameSuffix)
	@CheckPersist(allowEmpty = true)
	private List<String> shareUnitList;

	public static final String shareGroupList_FIELDNAME= "shareGroupList";
	@FieldDescribe("共享群组")
	@PersistentCollection(fetch = FetchType.EAGER)
	@OrderColumn(name = AbstractPersistenceProperties.orderColumn)
	@ContainerTable(name = TABLE + ContainerTableNameMiddle + shareGroupList_FIELDNAME, joinIndex = @Index(name = TABLE
			+ IndexNameMiddle + shareGroupList_FIELDNAME + JoinIndexNameSuffix))
	@ElementColumn(length = AbstractPersistenceProperties.organization_name_length, 
			name = ColumnNamePrefix + shareGroupList_FIELDNAME)
	@ElementIndex(name = TABLE + IndexNameMiddle + shareGroupList_FIELDNAME + ElementIndexNameSuffix)
	@CheckPersist(allowEmpty = true)
	private List<String> shareGroupList;

	public static final String editorList_FIELDNAME= "editorList";
	@FieldDescribe("可编辑人员")
	@PersistentCollection(fetch = FetchType.EAGER)
	@OrderColumn(name = AbstractPersistenceProperties.orderColumn)
	@ContainerTable(name = TABLE + ContainerTableNameMiddle + editorList_FIELDNAME, joinIndex = @Index(name = TABLE
			+ IndexNameMiddle + editorList_FIELDNAME + JoinIndexNameSuffix))
	@ElementColumn(length = AbstractPersistenceProperties.organization_name_length, 
			name = ColumnNamePrefix + editorList_FIELDNAME)
	@ElementIndex(name = TABLE + IndexNameMiddle + editorList_FIELDNAME + ElementIndexNameSuffix)
	@CheckPersist(allowEmpty = true)
	private List<String> editorList;
	
	public static final String shared_sequence_FIELDNAME= "shared_sequence";
	@FieldDescribe("自动生成，shared列表序号.")
	@Column( length = JpaObject.length_255B, name = ColumnNamePrefix + shared_sequence_FIELDNAME )
	@Index( name = TABLE + IndexNameMiddle + shared_sequence_FIELDNAME )
	@CheckPersist( allowEmpty = false)
	private String shared_sequence;
	
	public static final String folder_sequence_FIELDNAME = "folder_sequence";
	@FieldDescribe("自动生成，folder列表序号")
	@Column( length = JpaObject.length_255B, name = ColumnNamePrefix + folder_sequence_FIELDNAME )
	@Index( name = TABLE + IndexNameMiddle + folder_sequence_FIELDNAME )
	@CheckPersist( allowEmpty = false)
	private String folder_sequence;
	
	public static final String creator_sequence_FIELDNAME= "creator_sequence";
	@FieldDescribe("自动生成，creator列表序号.")
	@Column( length = JpaObject.length_255B, name = ColumnNamePrefix + creator_sequence_FIELDNAME )
	@Index( name = TABLE + IndexNameMiddle + creator_sequence_FIELDNAME )
	@CheckPersist( allowEmpty = false)
	private String creator_sequence;
	
	public static final String creatorUnit_sequence_FIELDNAME= "creatorUnit_sequence";
	@FieldDescribe("自动生成，creatorUnit列表序号.")
	@Column( length = JpaObject.length_255B, name = ColumnNamePrefix + creatorUnit_sequence_FIELDNAME )
	@Index( name = TABLE + IndexNameMiddle + creatorUnit_sequence_FIELDNAME )
	@CheckPersist( allowEmpty = false)
	private String creatorUnit_sequence;
	
	public static final String cooperative_sequence_FIELDNAME= "cooperative_sequence";
	@FieldDescribe("自动生成，cooperative列表序号.")
	@Column( length = JpaObject.length_255B, name = ColumnNamePrefix + cooperative_sequence_FIELDNAME )
	@Index( name = TABLE + IndexNameMiddle + cooperative_sequence_FIELDNAME )
	@CheckPersist( allowEmpty = false)
	private String cooperative_sequence;

	public static final String fileVersion_sequence_FIELDNAME= "fileVersion";
	@FieldDescribe("自动生成，版本号")
	@Column( name = ColumnNamePrefix + fileVersion_sequence_FIELDNAME )
	@Index( name = TABLE + IndexNameMiddle + name_FIELDNAME )
	@CheckPersist( allowEmpty = false)
	private Integer fileVersion = 1;

	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getCreator() {
		return creator;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatorUnit() {
		return creatorUnit;
	}

	public void setCreatorUnit(String creatorUnit) {
		this.creatorUnit = creatorUnit;
	}

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	public Boolean getShared() {
		return shared;
	}

	public void setShared(Boolean shared) {
		this.shared = shared;
	}

	public Integer getFileVersion() {
		return fileVersion;
	}

	public void setFileVersion(Integer fileVersion) {
		this.fileVersion = fileVersion;
	}

	public String getFolder_sequence() {
		return folder_sequence;
	}

	public String getCreator_sequence() {
		return creator_sequence;
	}

	public String getCreatorUnit_sequence() {
		return creatorUnit_sequence;
	}

	public String getShared_sequence() {
		return shared_sequence;
	}

	public void setFolder_sequence(String folder_sequence) {
		this.folder_sequence = folder_sequence;
	}

	public void setCreator_sequence(String creator_sequence) {
		this.creator_sequence = creator_sequence;
	}

	public void setCreatorUnit_sequence(String creatorUnit_sequence) {
		this.creatorUnit_sequence = creatorUnit_sequence;
	}

	public void setShared_sequence(String shared_sequence) {
		this.shared_sequence = shared_sequence;
	}

	public List<String> getEditorList() {
		return editorList;
	}

	public void setEditorList(List<String> editorList) {
		this.editorList = editorList;
	}

	public List<String> getSharePersonList() {
		return sharePersonList;
	}

	public List<String> getShareUnitList() {
		return shareUnitList;
	}

	public List<String> getShareGroupList() {
		return shareGroupList;
	}

	public void setSharePersonList(List<String> sharePersonList) {
		this.sharePersonList = sharePersonList;
	}

	public void setShareUnitList(List<String> shareUnitList) {
		this.shareUnitList = shareUnitList;
	}

	public void setShareGroupList(List<String> shareGroupList) {
		this.shareGroupList = shareGroupList;
	}

	public Boolean getCooperative() {
		return cooperative;
	}

	public String getCooperative_sequence() {
		return cooperative_sequence;
	}

	public void setCooperative(Boolean cooperative) {
		this.cooperative = cooperative;
	}

	public void setCooperative_sequence(String cooperative_sequence) {
		this.cooperative_sequence = cooperative_sequence;
	}
}