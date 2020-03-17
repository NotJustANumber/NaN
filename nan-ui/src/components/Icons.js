import React from 'react'
import { CheckCircleTwoTone, CloseCircleTwoTone, ClockCircleOutlined, TagsTwoTone, WarningTwoTone } from '@ant-design/icons';
function PassIcon(props) {
    return (<CheckCircleTwoTone twoToneColor="#48BB78" className={props.className} />);
}

function FailIcon(props) {
    return (<CloseCircleTwoTone twoToneColor="#e53e3e" className={props.className} />);
}

function SkippedIcon(props) {
    return (<WarningTwoTone twoToneColor="#D69E2E" className={props.className}></WarningTwoTone>);
}

function DurationIcon(props) {
    return (<ClockCircleOutlined className={props.className}></ClockCircleOutlined>);
}

function TotalTestsIcon(props) {

    return (<TagsTwoTone twoToneColor="#5a67d8" className={props.className} />);
}

export { PassIcon, FailIcon, SkippedIcon, DurationIcon, TotalTestsIcon };