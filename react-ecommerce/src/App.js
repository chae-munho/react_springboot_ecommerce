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
import CartPage from './component/pages/CaratPage';
import RegisterPage from './component/pages/RegisterPage';
import LoginPage from './component/pages/LoginPage';

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
            {/** */}
            <Route path='/login' element={<LoginPage></LoginPage>}></Route>
          </Routes>
          <Footer/>
      </CartProvider>
    </BrowserRouter>
   
  );
}

export default App;
