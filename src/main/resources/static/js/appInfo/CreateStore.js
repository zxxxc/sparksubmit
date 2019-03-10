function createStore(state){
    var list=[]
    function getState(){
        return state;
    }
    function change(action){
        state[action.type]=action.value;
        list.forEach(function(ele,index){
            ele()
        })

    }
    function addFn(hander){
        list.push(hander)
    }
    return {
        getState:getState,
        change:change,
        addFn:addFn
    }
}

