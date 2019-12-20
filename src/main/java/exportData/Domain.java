/**
 *
 */
package exportData;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * anubis-base
 * <description></description>
 * @author Johnny Liu
 * @date 2019-01-18
 */

@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
@Entity
@Table(name = "domain", schema = "base")
public class Domain {
    @Id
    @SequenceGenerator(name = "DOMAIN_ID_GENERATOR", sequenceName = "domain_id_seq", allocationSize = 1, schema = "base")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOMAIN_ID_GENERATOR")
    private Long id;
    @Column(name = "domain", nullable = true)
    private String domain;
    @Column(name = "refer", nullable = true)
    private String refer;
    @Column(name = "ip_acl", nullable = true)
    private String ipAcl;
    @Column(name = "cache", nullable = true)
    private String cache;
    @Column(name = "origion", nullable = true)
    private String origion;
    @Column(name = "protol", nullable = true)
    private String protocol;
    @Column(name = "cert_id", nullable = true)
    private Long certId;
    @Column(name = "status", nullable = true)
    private String status;
    /**
     * 域名类型: 泛域名和普通域名
     */
    @Column(name = "type", nullable = true)
    private String type;
    @Column(name = "line_id", nullable = true)
    private Long lineId;
    @Column(name = "cname", nullable = true)
    private String cname;
    @Column(name = "create_time", nullable = true)
    private Timestamp createTime;
    @Column(name = "update_time", nullable = true)
    private Timestamp updateTime;
    /**
     * 业务类型
     */
    @Column(name = "platform_id", nullable = true)
    private String platformId;
    /**
     * 下发的厂商[ 'qiniu','tencent' ]
     */
    @Column(name = "expected_vendor", nullable = true)
    private String expectedVendor;
    /**
     * 调度的厂商 [ 'qiniu' ]
     */
    @Column(name = "actual_vendor", nullable = true)
    private String acutalVendor;
    @Column(name = "approval_status", nullable = true)
    private String approvalStatus;
    @Column(name = "uuid", nullable = true)
    private String uuid;
    @Column(name = "http_header", nullable = true)
    private String httpHeader;
    @Column(name = "filter_query", nullable = true)
    private boolean filterQuery = false;
    @Column(name = "https_flag", nullable = true)
    private String httpsFlag;
    @Column(name = "https_config", nullable = true)
    private String httpsConfig;
    @Column(name = "user_code", nullable = true)
    private String userCode;
    @Column(name = "task_name", nullable = true)
    private String taskName;

    @Column(name = "app_name", nullable = true)
    private String appName;
    @Column(name = "stream_type", nullable = true)
    private String streamType;
}
