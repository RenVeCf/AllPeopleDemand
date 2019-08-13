package com.ipd.allpeopledemand.common.view;

import com.google.gson.Gson;
import com.ipd.allpeopledemand.bean.CheckVersionBean;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.xuexiang.xupdate.entity.UpdateEntity;
import com.xuexiang.xupdate.proxy.IUpdateParser;

import static com.ipd.allpeopledemand.common.config.IConstants.PACKAGE_NAME;
import static com.ipd.allpeopledemand.utils.AppUtils.getAppVersionCode;
import static com.ipd.allpeopledemand.utils.AppUtils.getAppVersionName;
import static com.ipd.allpeopledemand.utils.DateUtils.getTodayDateTime;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/7/3.
 */
public class CustomUpdateParser implements IUpdateParser {
    @Override
    public UpdateEntity parseJson(String json) throws Exception {
        CheckVersionBean result = new Gson().fromJson(json, CheckVersionBean.class);
        if (result != null) {
            boolean updateEntity = false;
            if (!getAppVersionName(ApplicationUtil.getContext(), PACKAGE_NAME).equals(result.getData().getVersion().getVersionNo())) {
                updateEntity = true;
            }
            return new UpdateEntity()
                    .setHasUpdate(updateEntity)//result.getData().getVersionYes().getNews() == 1 ? false : true)
                    .setForce(true)//result.getData().getVersionYes().getModify() == 1 ? true : false)
                    .setIsIgnorable(false)
//                    .setVersionCode(2)
                    .setVersionName(result.getData().getVersion().getVersionNo())
                    .setUpdateContent(result.getData().getVersion().getIntro())
                    .setIsAutoInstall(true)
                    .setDownloadUrl("http://xx.hanyu365.com.cn:8080/H5/allpeopledemand.apk");
        }
        return null;
    }
}
