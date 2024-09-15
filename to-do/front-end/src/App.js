import './App.css';
import AuthProvider from './components/security/AuthContext';
import TodoApp from './components/to-do/TodoApp';

import "bootstrap/dist/css/bootstrap.min.css"

function App() {
  return (
    <div className="App">
      <AuthProvider>
        <TodoApp/>
      </AuthProvider>
    </div>
  );
}

export default App;
