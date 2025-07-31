import React, { Component } from "react";
//Navigate : 조건이 충족되지 않을 때 특정 경로로 리디렉션(Navigation)하기 위해 사용
//useLocation() : 현재 라우트의 위치 객체 (경로, 쿼리 등)을 가져옴 : 로그인 후 원래 페이지로 돌아가기 위함
import { Navigate, useLocation } from "react-router-dom";
import ApiService from "./ApiService";

//로그인 여부를 검사하는 라우터 보호 컴포넌트
export const ProtectedRoute = ({element: Component}) => {
    const location = useLocation();

    return ApiService.isAuthenticated() ? (
        Component
    ):(
        <Navigate to="/login" replace state={{from: location}}/>
    );
};

//관리자 전용 페이지 접근을 제한하는 보호 라우트 컴포넌트
export const AdminRoute = ({element: Component}) => {
    const location = useLocation();

    return ApiService.isAdmin() ? (
        Component
    ):(
        <Navigate to="/login" replace state={{from: location}}/>
    );
};