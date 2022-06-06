package cn.com.tcc.ofa.erm.service.impl;

import cn.com.tcc.ofa.common.exception.RestException;
import cn.com.tcc.ofa.erm.enums.NodeTreeEnum;
import cn.com.tcc.ofa.erm.enums.RestExceptionEnum;
import cn.com.tcc.ofa.erm.mapper.ErmMenusMapper;
import cn.com.tcc.ofa.erm.model.po.*;
import cn.com.tcc.ofa.erm.service.*;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hsw
 * @date 2022/5/5 19:10
 */
@Service
public class ErmMenusServiceImpl extends ServiceImpl<ErmMenusMapper, ErmMenus> implements ErmMenusService {
    @Autowired
    ErmLicenseService ermLicenseService;

    @Autowired
    ErmLicenseTermsService ermLicenseTermsService;

    @Autowired
    ErmLocalDatabaseService ermLocalDatabaseService;

    @Autowired
    ErmPaymentPlanService ermPaymentPlanService;

    @Override
    public boolean removeByMenus(Integer type, Integer fatherId, Long id) {
        switch (type) {
            case 1:
                // 联系人表
                switch (fatherId) {
                    case 1:
                        // Contact Role Menu
                        break;
                    case 2:
                        // Contact Type Menu
                        break;
                    default:
                        throw new RestException(RestExceptionEnum.DELETE_MENUS_PARAM_ERROR_CODE);
                }
                break;
            case 2:
                // Payment表
                switch (fatherId) {
                    case 1:
                        // Payment Consortium Menu
                        break;
                    case 2:
                        // Payment Payee Menu
                        break;
                    case 3:
                        // Payment Payer Menu
                        break;
                    case 4:
                        // Payment Type Menu
                        break;
                    case 5:
                        // Transaction Currency Code
                        List<ErmPaymentPlan> ermPaymentPlans = ermPaymentPlanService.list(new LambdaQueryWrapper<ErmPaymentPlan>().eq(ErmPaymentPlan::getCurrency, id));
                        if (CollUtil.isNotEmpty(ermPaymentPlans)) {
                            throw new RestException(RestExceptionEnum.DELETE_MENUS_ERROR_CODE);
                        }
                        break;
                    default:
                        throw new RestException(RestExceptionEnum.DELETE_MENUS_PARAM_ERROR_CODE);
                }
                break;
            case 3:
                // 许可协议表
                List<ErmLicense> licenses = null;
                LambdaQueryWrapper<ErmLicense> wrapper = new LambdaQueryWrapper<>();
                switch (fatherId) {
                    case 1:
                        // License Authorized Users Menu
                        List<ErmLicenseTerms> ermLicenseTerms = ermLicenseTermsService.list(new LambdaQueryWrapper<ErmLicenseTerms>().select(ErmLicenseTerms::getAuthorizedUsersId));
                        if (CollUtil.isNotEmpty(ermLicenseTerms)) {
                            for (ErmLicenseTerms ermLicenseTerm : ermLicenseTerms) {
                                String authorizedUsersId = ermLicenseTerm.getAuthorizedUsersId();
                                if (authorizedUsersId.contains(id + "")) {
                                    throw new RestException(RestExceptionEnum.DELETE_MENUS_ERROR_CODE);
                                }
                            }
                        }
                        break;
                    case 2:
                        // License Physical Location Menu
                        licenses = ermLicenseService.list(wrapper.eq(ErmLicense::getPhysicalLocation, id));
                        break;
                    case 3:
                        // License Remote Access Menu
                        break;
                    case 4:
                        // License Status Menu
                        wrapper.clear();
                        licenses = ermLicenseService.list(wrapper.eq(ErmLicense::getStatus, id));
                        break;
                    case 5:
                        // License Terms Of Use Rights Menu
                        break;
                    case 6:
                        // License Type Menu
                        wrapper.clear();
                        licenses = ermLicenseService.list(wrapper.eq(ErmLicense::getType, id));
                        break;
                    default:
                        throw new RestException(RestExceptionEnum.DELETE_MENUS_PARAM_ERROR_CODE);
                }
                if (CollUtil.isNotEmpty(licenses)) {
                    throw new RestException(RestExceptionEnum.DELETE_MENUS_ERROR_CODE);
                }
                break;
            case 4:
                // 说明表
                switch (fatherId) {
                    case 1:
                        // Note Status Menu
                        break;
                    case 2:
                        // Note Type Menu
                        break;
                    default:
                        throw new RestException(RestExceptionEnum.DELETE_MENUS_PARAM_ERROR_CODE);
                }
                break;
            case 5:
                // 资源库表
                switch (fatherId) {
                    case 1:
                        // Resource Status Menu
                        List<ErmLocalDatabase> databaseList = ermLocalDatabaseService.list(new LambdaQueryWrapper<ErmLocalDatabase>()
                                .eq(ErmLocalDatabase::getStatus, id));
                        if (CollUtil.isNotEmpty(databaseList)) {
                            throw new RestException(RestExceptionEnum.DELETE_MENUS_ERROR_CODE);
                        }
                        break;
                    default:
                        throw new RestException(RestExceptionEnum.DELETE_MENUS_PARAM_ERROR_CODE);
                }
                break;
            default:
                throw new RestException(RestExceptionEnum.DELETE_MENUS_PARAM_ERROR_CODE);
        }
        return removeById(id);
    }

    @Override
    public List<Map<String, Object>> getNodeTree() {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", NodeTreeEnum.CONTACT_MENUS.getId());
        map1.put("nodeName", NodeTreeEnum.CONTACT_MENUS.getNodeName());
        List<Map<String, Object>> list1 = getList(1);
        map1.put("children", list1);
        list.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", NodeTreeEnum.PAYMENT_MENUS.getId());
        map2.put("nodeName", NodeTreeEnum.PAYMENT_MENUS.getNodeName());
        List<Map<String, Object>> list2 = getList(2);
        map2.put("children", list2);
        list.add(map2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("id", NodeTreeEnum.LICENSE_MENUS.getId());
        map3.put("nodeName", NodeTreeEnum.LICENSE_MENUS.getNodeName());
        List<Map<String, Object>> list3 = getList(3);
        map3.put("children", list3);
        list.add(map3);

        Map<String, Object> map4 = new HashMap<>();
        map4.put("id", NodeTreeEnum.NOTE_MENUS.getId());
        map4.put("nodeName", NodeTreeEnum.NOTE_MENUS.getNodeName());
        List<Map<String, Object>> list4 = getList(4);
        map4.put("children", list4);
        list.add(map4);

        Map<String, Object> map5 = new HashMap<>();
        map5.put("id", NodeTreeEnum.RESOURCE_MENUS.getId());
        map5.put("nodeName", NodeTreeEnum.RESOURCE_MENUS.getNodeName());
        List<Map<String, Object>> list5 = getList(5);
        map5.put("children", list5);
        list.add(map5);

        return list;
    }

    public List<Map<String, Object>> getList(Integer parentId) {
        switch (parentId) {
            case 1:
                List<Map<String, Object>> contactList = new ArrayList<>();
                Map<String, Object> contactMap1 = new HashMap<>();
                contactMap1.put("id", NodeTreeEnum.CONTACT_ROLE_MENU.getId());
                contactMap1.put("nodeName", NodeTreeEnum.CONTACT_ROLE_MENU.getNodeName());

                HashMap<String, Object> contactMap2 = new HashMap<>();
                contactMap2.put("id", NodeTreeEnum.CONTACT_TYPE_MENU.getId());
                contactMap2.put("nodeName", NodeTreeEnum.CONTACT_TYPE_MENU.getNodeName());

                contactList.add(contactMap1);
                contactList.add(contactMap2);
                return contactList;
            case 2:
                List<Map<String, Object>> paymentList = new ArrayList<>();

                Map<String, Object> paymentMap1 = new HashMap<>();
                paymentMap1.put("id", NodeTreeEnum.PAYMENT_CONSORTIUM_MENU.getId());
                paymentMap1.put("nodeName", NodeTreeEnum.PAYMENT_CONSORTIUM_MENU.getNodeName());

                HashMap<String, Object> paymentMap2 = new HashMap<>();
                paymentMap2.put("id", NodeTreeEnum.PAYMENT_PAYEE_MENU.getId());
                paymentMap2.put("nodeName", NodeTreeEnum.PAYMENT_PAYEE_MENU.getNodeName());

                HashMap<String, Object> paymentMap3 = new HashMap<>();
                paymentMap3.put("id", NodeTreeEnum.PAYMENT_PAYER_MENU.getId());
                paymentMap3.put("nodeName", NodeTreeEnum.PAYMENT_PAYER_MENU.getNodeName());

                HashMap<String, Object> paymentMap4 = new HashMap<>();
                paymentMap4.put("id", NodeTreeEnum.PAYMENT_TYPE_MENU.getId());
                paymentMap4.put("nodeName", NodeTreeEnum.PAYMENT_TYPE_MENU.getNodeName());

                HashMap<String, Object> paymentMap5 = new HashMap<>();
                paymentMap5.put("id", NodeTreeEnum.TRANSACTION_CURRENCY_CODE.getId());
                paymentMap5.put("nodeName", NodeTreeEnum.TRANSACTION_CURRENCY_CODE.getNodeName());

                paymentList.add(paymentMap1);
                paymentList.add(paymentMap2);
                paymentList.add(paymentMap3);
                paymentList.add(paymentMap4);
                paymentList.add(paymentMap5);
                return paymentList;
            case 3:
                List<Map<String, Object>> licenseList = new ArrayList<>();

                Map<String, Object> licenseMap1 = new HashMap<>();
                licenseMap1.put("id", NodeTreeEnum.LICENSE_AUTHORIZED_USERS_MENU.getId());
                licenseMap1.put("nodeName", NodeTreeEnum.LICENSE_AUTHORIZED_USERS_MENU.getNodeName());

                Map<String, Object> licenseMap2 = new HashMap<>();
                licenseMap2.put("id", NodeTreeEnum.LICENSE_PHYSICAL_LOCATION_MENU.getId());
                licenseMap2.put("nodeName", NodeTreeEnum.LICENSE_PHYSICAL_LOCATION_MENU.getNodeName());

                Map<String, Object> licenseMap3 = new HashMap<>();
                licenseMap3.put("id", NodeTreeEnum.LICENSE_REMOTE_ACCESS_MENU.getId());
                licenseMap3.put("nodeName", NodeTreeEnum.LICENSE_REMOTE_ACCESS_MENU.getNodeName());

                Map<String, Object> licenseMap4 = new HashMap<>();
                licenseMap4.put("id", NodeTreeEnum.LICENSE_STATUS_MENU.getId());
                licenseMap4.put("nodeName", NodeTreeEnum.LICENSE_STATUS_MENU.getNodeName());

                Map<String, Object> licenseMap5 = new HashMap<>();
                licenseMap5.put("id", NodeTreeEnum.LICENSE_TERMS_OF_USE_RIGHTS_MENU.getId());
                licenseMap5.put("nodeName", NodeTreeEnum.LICENSE_TERMS_OF_USE_RIGHTS_MENU.getNodeName());

                Map<String, Object> licenseMap6 = new HashMap<>();
                licenseMap6.put("id", NodeTreeEnum.LICENSE_TYPE_MENU.getId());
                licenseMap6.put("nodeName", NodeTreeEnum.LICENSE_TYPE_MENU.getNodeName());

                licenseList.add(licenseMap1);
                licenseList.add(licenseMap2);
                licenseList.add(licenseMap3);
                licenseList.add(licenseMap4);
                licenseList.add(licenseMap5);
                licenseList.add(licenseMap6);
                return licenseList;
            case 4:
                List<Map<String, Object>> noteList = new ArrayList<>();

                Map<String, Object> noteMap1 = new HashMap<>();
                noteMap1.put("id", NodeTreeEnum.NOTE_STATUS_MENU.getId());
                noteMap1.put("nodeName", NodeTreeEnum.NOTE_STATUS_MENU.getNodeName());

                Map<String, Object> noteMap2 = new HashMap<>();
                noteMap2.put("id", NodeTreeEnum.NOTE_TYPE_MENU.getId());
                noteMap2.put("nodeName", NodeTreeEnum.NOTE_TYPE_MENU.getNodeName());

                noteList.add(noteMap1);
                noteList.add(noteMap2);
                return noteList;
            case 5:
                List<Map<String, Object>> resourceList = new ArrayList<>();

                Map<String, Object> resourceMap1 = new HashMap<>();
                resourceMap1.put("id", NodeTreeEnum.RESOURCE_STATUS_MENU.getId());
                resourceMap1.put("nodeName", NodeTreeEnum.RESOURCE_STATUS_MENU.getNodeName());

                resourceList.add(resourceMap1);
                return resourceList;
        }
        return null;
    }
}
