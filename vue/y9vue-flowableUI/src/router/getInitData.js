/*
 * @Author: your name
 * @Date: 2021-12-22 16:50:57
 * @LastEditTime: 2024-03-28 09:46:16
 * @LastEditors: mengjuhua
 * @Description: 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 * @FilePath: /sz-team-frontend-9.5.x/y9vue-flowableUI/src/router/getData.js
 */

export async function getLoginInfo() {
    let sessionObj = JSON.parse(sessionStorage.getItem(import.meta.env.VUE_APP_SSO_SITETOKEN_KEY));
    return await fetch(import.meta.env.VUE_APP_HOST + 'y9home/api/rest/index/getLoginInfo', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
            Authorization: 'Bearer ' + sessionObj.access_token
        }
    })
        .then((res) => {
            return res.json();
        })
        .then((res) => {
            // console.log(res.data);
            sessionStorage.setItem('getLoginInfo', 'true');
            sessionStorage.setItem('departmentMapList', JSON.stringify(res.data.departmentMapList));
            sessionStorage.setItem('positionList', JSON.stringify(res.data.positionList));
            return res;
        })
        .catch((e) => {
            console.log(e);
            // sessionStorage.clear();
            // window.location = window.location.origin + window.location.pathname;
            sessionStorage.setItem('getLoginInfo', 'false');
            window.location = window.location.origin + window.location.pathname;
        });
}
