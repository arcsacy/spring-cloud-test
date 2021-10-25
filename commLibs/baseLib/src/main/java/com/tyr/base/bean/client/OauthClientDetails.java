package com.tyr.base.bean.client;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@TableName("oauth_client_details")
public class OauthClientDetails extends Model<OauthClientDetails> {

    @TableId(type = IdType.ASSIGN_ID)
    private String clientId;
    private String resourceIds;
    private String clientSecret;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private int accessTokenValidity;
    private int refreshTokenValidity;
    private String additionalInformation;
    private String autoapprove;

}
