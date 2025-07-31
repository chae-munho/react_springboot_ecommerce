import React, {createContext, useReducer, useContext, useEffect} from "react";
//1. ContextAPI 기본개념 : createContext, useContext, Provider가 왜 필요한지
//2. useReducer 구조 : dispatch -> reducer -> state 변경 흐름 익히기
//3. localStorage 사용법 : 상태를 브라우저에 저장/ 불러오기
//4. useEffect 사용 이유 : 상태가 바뀔 때 로직 실행하는 구조 익히기
//5. 전역 상태를 컴포넌트에서 어떻게 쓰느가 : STATE

const CartContext = createContext(); 


const initialState = {
    cart: JSON.parse(localStorage.getItem('cart')) || [],
}

const cartReducer = (state, action) => {
    switch(action.type) {
        case 'ADD_ITEM': {
            //identify exisitng item
            const existingItem = state.cart.find(item => item.id === action.payload.id);
            let newCart;

            if(existingItem){
                newCart = state.cart.map(item =>
                    item.id === action.payload.id
                    ? {...item, quantity: item.quantity + 1}
                    : item
                );
            }else {
                newCart = [...state.cart, {...action.payload, quantity: 1 }];
            }
            localStorage.setItem('cart', JSON.stringify(newCart));
            return {...state, cart:newCart};
        }

        case 'REMOVE_ITEM':{
            const newCart = state.cart.filter(item=> item.id !== action.payload.id);
            localStorage.setItem('cart', JSON.stringify(newCart));
            return {...state, cart:newCart};
        }

        case 'INCREMENT_ITEM': {
            const newCart = state.cart.map(item=>
                item.id === action.payload.id
                ? {...item, quantity: item.quantity + 1}
                :item
            );
            localStorage.setItem('cart', JSON.stringify(newCart));
            return {...state, cart:newCart};
        }

        case 'DECREMENT_ITEM': {
            const newCart = state.cart.map(item =>
                item.id === action.payload.id && item.quantity > 1
                ? {...item, quantity: item.quantity -1}
                :item
            )
            localStorage.setItem('cart', JSON.stringify(newCart));
            return {...state, cart:newCart};
        }

        case 'CLEAR_CART': {
            localStorage.removeItem('cart');
            return {...state, cart:[]};
        }
        default:
            return state;
    }
}
export const CartProvider = ({children}) => {
    const [state, dispatch] = useReducer(cartReducer, initialState);

    useEffect(() => {
        localStorage.setItem('cart', JSON.stringify(state.cart));
    }, [state.cart]);

    return (
        <CartContext.Provider value={{cart : state.cart, dispatch}}>{children}</CartContext.Provider>
    )
}

export const useCart = () => useContext(CartContext);
