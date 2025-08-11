//npm i axios
//npm i react-router-dom
import './App.css';
import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom';
import { ProtectedRoute, AdminRoute } from './service/Guard';
import Navbar from './component/common/Navbar';
import Footer from './component/common/footer';
import {CartProvider} from "./component/context/CartContext"
import Home from './component/pages/Home';
import ProductDetailsPage from './component/pages/ProductDetailsPage';
import CategoryListPage from './component/pages/CategoryListPage';
import CategoryProductsPage from './component/pages/CategoryProductsPage';
import CartPage from './component/pages/CartPage';
import RegisterPage from './component/pages/RegisterPage';
import LoginPage from './component/pages/LoginPage';
import ProfilePage from './component/pages/ProfilePage';
import AddressPage from './component/pages/AddressPage';
import AdminPage from './component/admin/AdminPage';
import AdminCategoryPage from './component/admin/AdminCategory';
import AddCategory from './component/admin/AddCategoryPage';
import EditCategory from './component/admin/EditCategory';
import AdminProductPage from './component/admin/AdminProduectPage';
import AddProductPage from './component/admin/AddProductPage';
import EditProductPage from './component/admin/EditProductPage';
function App() {
  return (
    <BrowserRouter>
      <CartProvider>
        <Navbar/>
          <Routes>
            {/**OUR ROUTES */}
            {/* "/" 경로로 들어오면 <Home/> 컴포넌트를 화면에 표시하라 */}
            <Route exact path='/' element={<Home/>}/>
            {/* 상품 상세 보기 경로 */}
            <Route path='/product/:productId' element={<ProductDetailsPage/>}></Route>
            {/*카테고리 리스트 페이지 경로 */}
            <Route path='/categories' element={<CategoryListPage/>}/>
            {/*카테고리 아이디에 해당하는 모든 상품 나열 */}
            <Route path='/category/:categoryId' element={<CategoryProductsPage/>}></Route>
            {/*Add to Cart로 선택한 아이템들 모두 보기 */}
            <Route path='/cart' element={<CartPage></CartPage>}></Route>
            {/*회원가입 resgister*/}
            <Route path='/register' element={<RegisterPage></RegisterPage>}></Route>
            {/**로그인 */}
            <Route path='/login' element={<LoginPage></LoginPage>}></Route>
            {/**프로필 페이지 */}
            <Route path='/profile' element={<ProtectedRoute element={<ProfilePage></ProfilePage>}></ProtectedRoute>}></Route>

            {/*주소 입력 또는 수정 */}
            <Route path='/add-address' element={<ProtectedRoute element={<AddressPage></AddressPage>}></ProtectedRoute>}></Route>
            <Route path='/edit-address' element={<ProtectedRoute element={<AddressPage></AddressPage>}></ProtectedRoute>}></Route>

            {/*관리자 페이지 */}
            <Route path='/admin' element={<AdminRoute element={<AdminPage></AdminPage>}></AdminRoute>}></Route>

            {/*관리자 카테고리 페이지 */}
            <Route path='/admin/categories' element={<AdminRoute element={<AdminCategoryPage></AdminCategoryPage>}></AdminRoute>}></Route>
            {/*관리자 카테고리 추가 페이지 */}
            <Route path='/admin/add-category' element={<AdminRoute element={<AddCategory></AddCategory>}></AdminRoute>}></Route>
            {/*관리자 카테고리 수정 페이지 */}
            <Route path='/admin/edit-category/:categoryId' element={<AdminRoute element={<EditCategory></EditCategory>}></AdminRoute>}></Route>
            {/*관리자 상품 페이지 */}
            <Route path='/admin/products' element={<AdminRoute element={<AdminProductPage></AdminProductPage>}></AdminRoute>}></Route>
            {/*관리자 상품 등록 페이지 */}
            <Route path='/admin/add-product' element={<AdminRoute element={<AddProductPage></AddProductPage>}></AdminRoute>}></Route>

            {/**관리자 상품 수정 페이지 */}
            <Route path='/admin/edit-product/:productId' element={<AdminRoute element={<EditProductPage></EditProductPage>}></AdminRoute>}></Route>
          </Routes>
          <Footer/>
      </CartProvider>
    </BrowserRouter>
   
  );
}

export default App;
