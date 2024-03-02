import { useState, useRef, useEffect } from 'react'
import { Wrap,WrapItem,Spinner,Text } from '@chakra-ui/react'
import SidebarWithHeader from "./components/shared/SideBar.jsx";

import {deleteStudent, getStudents} from "./services/client.js";
import CardWithImage from "./components/Card";
import DrawerForm from "./components/DrawerForm.jsx";
import {errorNotification} from "./services/Notification.js";

function App() {
    const [isLoading, setIsLoading] = useState(false)
    const [students, setStudents] = useState([])
    const [err, setError] = useState("")

    const fetchStudents = () => {
        setIsLoading(true)
        setTimeout(() => {
            getStudents().then(res => {
                setStudents(res.data)
            }).catch(err => {
                setError(err.response.data.message)
                errorNotification(err.code, err.response.data.message)
            }).finally(() => {
                setIsLoading(false)
            })
        },500)
    }
    useEffect(() => {
        fetchStudents();
    },[])


    if(isLoading) {
        return (
            <SidebarWithHeader>
                <Spinner thickness='4px' speed='0.65s' emptyColor='gray.200' color='blue.500' size='xl'/>
            </SidebarWithHeader>
        )
    }

    if(err) {
        return (
            <SidebarWithHeader>
                <DrawerForm fetchStudents={fetchStudents}/>
                <Text mt={5}>Ooops error occurred</Text>
            </SidebarWithHeader>
        )
    }

    if(students.length < 1) {
        return (
            <SidebarWithHeader>
                <DrawerForm fetchStudents={fetchStudents}/>
                <Text mt={5}>No students available</Text>
            </SidebarWithHeader>
        )
    }

  return (
          <SidebarWithHeader>
              <DrawerForm fetchStudents={fetchStudents}/>
              <Wrap justify={"center"} spacing={"30px"}>
                  {students.map(({id, firstName, lastName, email, age, phoneNumber, gender},idx) => (
                      <WrapItem key={id}>
                          <CardWithImage id={id} name={`${firstName} ${lastName}`} email={email} age={age} gender={gender} phoneNumber={phoneNumber} fetchStudent={fetchStudents}/>
                      </WrapItem>
                      ))}
              </Wrap>
          </SidebarWithHeader>
  )
}

export default App
