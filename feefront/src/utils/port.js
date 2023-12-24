import * as request from "@/utils/request";
// import * as request

//用户登录
export function LoginUser(params) {
    return request.POST2(
        '/user/login',
        params
    )
}

//用户注销
export function Logout(params) {
    return request.POST2(
        '/user/logout',
        params
    )
}


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

// 获取总收入
export function SumReceipt() {
    return request.GET(
        '/income/sum',
    )
}

// 获取总支出
export function SumPayout() {
    return request.GET(
        '/pay/sum',
    )
}

// 获取总预收
export function SumBudget() {
    return request.GET(
        '/income/count',
    )
}


//查询所有支出信息
export function PayAll() {
    return request.GET(
        '/pay/all',
    )
}

//根据title收入信息
export function searchItem(params) {
    return request.GET(
        '/pay/searchItem',
        params
    )
}

//编辑收入信息
export function PayEdit(params) {
    return request.POST2(
        '/pay/edit',
        params
    )
}

//新增班费收入
export function PayAdd(params) {
    return request.POST2(
        '/pay/add',
        params
    )
}

//删除班费收入
export function PayDel(params) {
    return request.POST2(
        '/pay/del',
        params
    )
}


//获取学生
export function StudentAll() {
    return request.GET(
        '/stu/all'
    )
}


//获取一条details详细数据记录
export function DetailsByIncomeId(params) {
    return request.GET(
        '/details/incomeid',
        params
    )
}


//修改Details记录
export function DetailsEdit(params) {
    return request.POST2(
        '/details/edit',
        params
    )
}


// 获取已完成缴费状态
export function SuccessStatus() {
    return request.GET(
        '/income/success'
    )
}

// 获取未完成已经待处理缴费状态
export function WarnStatus() {
    return request.GET(
        '/income/warning'
    )
}