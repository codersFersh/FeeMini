import * as request from "@/utils/request";
// import * as request

//查询所有收入信息
export function IncomeAll() {
    return request.GET(
        '/income/all',
    )
}

//根据title收入信息
export function searchTitle(params) {
    return request.GET(
        '/income/searchTitle',
        params
    )
}

//编辑收入信息
export function IncomeEdit(params) {
    return request.POST2(
        '/income/edit',
        params
    )
}

//新增班费收入
export function IncomeAdd(params) {
    return request.POST2(
        '/income/add',
        params
    )
}

//删除班费收入
export function IncomeDel(params) {
    return request.POST2(
        '/income/del',
        params
    )
}